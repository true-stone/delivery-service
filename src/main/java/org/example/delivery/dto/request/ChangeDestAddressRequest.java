package org.example.delivery.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangeDestAddressRequest(
    @NotBlank String destAddress
) {
}
