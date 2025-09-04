package org.example.delivery.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record LoginResponse(

    @Schema(description = "토큰의 타입을 나타냅니다. 보통 'Bearer'와 같은 값으로 설정되며, 클라이언트가 API 요청 시 이 값과 함께 접근 토큰을 헤더에 포함하여 전송합니다. <br>"
        + "예: 'Bearer'",
        requiredMode = REQUIRED)
    String grantType,

    @Schema(description = "사용자가 인증을 받은 후 발급되는 접근 토큰입니다. 이 토큰을 사용하여 보호된 API에 접근할 수 있습니다. <br>"
        + "이 토큰은 일정 기간 유효하며, 만료된 후에는 다시 로그인해야 합니다.",
        requiredMode = REQUIRED)
    String accessToken
) {}
