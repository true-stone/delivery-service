package org.example.delivery.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.delivery.security.UserDetailsImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Component
public class JwtProvider {

    public static final String AUTHORIZATION_HEADER = HttpHeaders.AUTHORIZATION;
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String CLAIM_ID = "id";
    private static final String CLAIM_ROLES = "roles";
    private static final String CLAIM_TYP = "typ";
    private static final String CLAIM_JTI = "jti";
    private static final String TYP_REFRESH = "refresh";


    private final JwtProperties props;
    private SecretKey key;

    public JwtProvider(JwtProperties props) {
        this.props = props;
    }

    @PostConstruct
    void initKey() {
        // secretKey 값을 Base64 를 Decode 해서 Key 변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(props.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    private JwtParser parser() {
        return Jwts.parser().verifyWith(key).build();
    }

    /**
     * Authorization 헤더에서 Bearer 토큰 추출
     */
    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION_HEADER);
        if (bearer != null && bearer.startsWith(TOKEN_PREFIX)) {
            return bearer.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    /**
     * 토큰 검증 (예외 던지지 않고 true/false)
     */
    public boolean validate(String token) {
        try {
            parser().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 검증 실패 시 예외를 던지는 버전이 필요하면 분리
     */
    public void assertValidToken(String token) {
        parser().parseSignedClaims(token); // 유효하지 않으면 JwtException 발생
    }

    /**
     * Claims 추출 (유효하지 않으면 JwtException)
     */
    private Claims claims(String token) {
        return parser().parseSignedClaims(token).getPayload();
    }

    public boolean isRefreshToken(String token) {
        return Objects.equals(claims(token).get(CLAIM_TYP), TYP_REFRESH);
    }

    public String getSubject(String token) {
        return claims(token).getSubject();
    }

    public String getJti(String token) {
        Object v = claims(token).get(CLAIM_JTI);
        return v != null ? v.toString() : null;
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        Object v = claims(token).get(CLAIM_ROLES);
        return (v instanceof List<?> list) ? list.stream().map(String::valueOf).toList() : List.of();
    }

    public Instant getExpiry(String token) {
        return claims(token).getExpiration().toInstant();
    }

    /**
     * 토큰 → Authentication (UserDetailsImpl + 권한 매핑)
     */
    public Authentication getAuthentication(String token) {
        Claims claims = claims(token);
        if (claims == null) throw new IllegalArgumentException("Invalid token: claims are null");

        String username = claims.getSubject();
        Long id = (Long) claims.get(CLAIM_ID);

        // 권한 클레임이 있다면 매핑 (없으면 빈 권한)
        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) claims.getOrDefault(CLAIM_ROLES, Collections.emptyList());
        var authorities = roles.stream()
            .map(SimpleGrantedAuthority::new)
            .toList();

        var principal = new UserDetailsImpl(id, username, "");
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * AccessToken 생성
     */
    public String createAccessToken(String subject, List<String> roles, String jti) {
        Instant now = Instant.now();
        Instant exp = now.plus(props.getAccessTtlMinutes(), ChronoUnit.MINUTES);

        var b = Jwts.builder()
            .issuer(props.getIssuer())
            .subject(subject)
            .issuedAt(Date.from(now))
            .expiration(Date.from(exp))
            .signWith(key); // HS256

        if (roles != null && !roles.isEmpty()) b.claim(CLAIM_ROLES, roles);
        if (jti != null) b.claim(CLAIM_JTI, jti);

        return b.compact();
    }

    /**
     * Authentication 으로 AccessToken 발급
     */
    public String generateAccessToken(Authentication authentication) {
        Object p = authentication.getPrincipal();
        String subject = (p instanceof UserDetails ud)
            ? ud.getUsername() : authentication.getName();

        List<String> roles = authentication.getAuthorities().stream()
            .map(a -> a.getAuthority().replaceFirst("^ROLE_", ""))
            .toList();

        return createAccessToken(subject, roles, UUID.randomUUID().toString());
    }

    /**
     * RefreshToken 생성
     */
    public String createRefreshToken(String subject, String jti) {
        Instant now = Instant.now();
        Instant exp = now.plus(props.getRefreshTtlDays(), ChronoUnit.DAYS);

        var b = Jwts.builder()
            .issuer(props.getIssuer())
            .subject(subject)
            .issuedAt(Date.from(now))
            .expiration(Date.from(exp))
            .claim(CLAIM_TYP, TYP_REFRESH)
            .signWith(key);

        if (jti != null) b.claim(CLAIM_JTI, jti);

        return b.compact();
    }

    /**
     * 만료일자
     */
    public LocalDateTime getTokenExpireDateTime(String token) {
        return claims(token)
            .getExpiration()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }
}

