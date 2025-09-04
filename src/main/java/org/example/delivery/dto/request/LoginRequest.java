package org.example.delivery.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank
    @Schema(description = "사용자 아이디", example = "username1")
    String username,

    @NotBlank
    @Schema(description = "사용자 비밀번호", example = "password@1234")
    String password
) {}
