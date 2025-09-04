package org.example.delivery.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
    @NotBlank
    @Schema(description = "사용자 아이디", example = "username2")
    String username,

    @NotBlank
    @Schema(description = "사용자 비밀번호", example = "password1234!")
    String password,


    @NotBlank
    @Schema(description = "사용자 이름", example = "김길동")
    String name
) {}
