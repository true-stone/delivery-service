package org.example.delivery.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.delivery.common.response.code.ErrorCode;
import org.example.delivery.dto.request.LoginRequest;
import org.example.delivery.dto.request.SignUpRequest;
import org.example.delivery.dto.response.LoginResponse;
import org.example.delivery.entity.User;
import org.example.delivery.exception.BusinessException;
import org.example.delivery.jwt.JwtProvider;
import org.example.delivery.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(@Valid SignUpRequest request) {
        // 비밀번호 검증
        if (!PasswordPolicy.valid(request.password())) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "비밀번호가 정책에 맞지 않습니다.");
        }

        // 사용자 아이디 중복 확인
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "이미 사용중인 아이디입니다.");
        });

        // 사용자 생성
        User user = User.builder()
            .username(request.username())
            .password(passwordEncoder.encode(request.password()))
            .name(request.name())
            .build();

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            request.username(), request.password());
        Authentication authenticate = authenticationProvider.authenticate(authenticationToken);

        // AccessToken 토큰 생성
        String accessToken = jwtProvider.generateAccessToken(authenticate);

        // TODO 리프레시 토큰 추가

        return LoginResponse.builder()
            .grantType(JwtProvider.TOKEN_PREFIX)
            .accessToken(accessToken)
            .build();
    }
}
