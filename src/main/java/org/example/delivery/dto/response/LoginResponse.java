package org.example.delivery.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
    String grantType,
    String accessToken
) {}
