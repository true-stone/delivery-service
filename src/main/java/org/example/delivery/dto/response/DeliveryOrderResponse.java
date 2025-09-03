package org.example.delivery.dto.response;

import lombok.Builder;
import org.example.delivery.code.DeliveryStatus;
import org.example.delivery.entity.DeliveryOrder;

import java.time.LocalDateTime;

@Builder
public record DeliveryOrderResponse(
    Long id,
    String orderUid,
    DeliveryStatus status,
    String pickupAddress,
    String destAddress,
    int changeCount,
    LocalDateTime paidAt,
    LocalDateTime acceptedAt,
    LocalDateTime pickedUpAt,
    LocalDateTime deliveredAt,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static DeliveryOrderResponse from(DeliveryOrder o) {
        return DeliveryOrderResponse.builder()
            .id(o.getId())
            .orderUid(o.getOrderUid())
            .status(o.getStatus())
            .pickupAddress(o.getPickupAddress())
            .destAddress(o.getDestAddress())
            .changeCount(o.getChangeCount())
            .paidAt(o.getPaidAt())
            .acceptedAt(o.getAcceptedAt())
            .pickedUpAt(o.getPickedUpAt())
            .deliveredAt(o.getDeliveredAt())
            .createdAt(o.getCreatedAt())
            .updatedAt(o.getUpdatedAt())
            .build();
    }
}
