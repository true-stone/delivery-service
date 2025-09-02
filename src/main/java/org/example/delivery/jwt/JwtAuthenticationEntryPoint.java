package org.example.delivery.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.delivery.common.response.ApiResponse;
import org.example.delivery.common.response.code.ErrorCode;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode error;

        if (authException instanceof InsufficientAuthenticationException
            && authException.getCause() instanceof ExpiredJwtException) {
            error = ErrorCode.UNAUTHORIZED_TOKEN_EXPIRED;
        } else if (authException instanceof BadCredentialsException) {
            error = ErrorCode.UNAUTHORIZED_TOKEN_ERROR;
        } else {
            error = ErrorCode.UNAUTHORIZED;
        }

        if (response.isCommitted()) return;

        response.setStatus(error.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(mapper.writeValueAsString(ApiResponse.of(error)));
    }
}