package org.example.delivery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.delivery.common.response.ApiResponse;
import org.example.delivery.dto.request.LoginRequest;
import org.example.delivery.dto.request.SignUpRequest;
import org.example.delivery.dto.response.LoginResponse;
import org.example.delivery.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "인증", description = "인증 관련 API")
public class AuthController {

    private final AuthService authService;

    /**
     * 회원가입
     */
    @Operation(summary = "회원가입 API", description = "서비스에 가입합니다.")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signUp(@Valid @RequestBody SignUpRequest request) {
        authService.signUp(request);
        return ApiResponse.created();
    }

    /**
     * 로그인
     */
    @Operation(summary = "로그인 API", description = "로그인을 통해 토큰을 발급받습니다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.ok(authService.login(request));
    }
}
