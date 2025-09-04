package org.example.delivery.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "배달 도착지 주소 변경")
public record ChangeDestAddressRequest(

    @NotBlank
    @Schema(description = "변경할 주소")
    String destAddress
) {
}
