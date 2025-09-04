package org.example.delivery.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.example.delivery.code.DeliveryStatus;
import org.example.delivery.entity.DeliveryOrder;

import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "배달 주문 조회 응답")
@Builder
public record DeliveryOrderResponse(

    @Schema(
        description = "배달 주문 내부 식별자",
        example = "123",
        requiredMode = REQUIRED)
    Long id,

    @Schema(
        description = "외부 노출용 주문 UID (UUID v4, 36자)",
        example = "7f1c1a2b-3d4e-5f60-a1b2-c3d4e5f60718",
        requiredMode = REQUIRED
    )
    String orderUid,

    @Schema(description = "배달 주문 상태",
        example = "DISPATCHING",
        implementation = DeliveryStatus.class,
        requiredMode = REQUIRED
    )
    DeliveryStatus status,

    @Schema(
        description = "픽업(수거) 주소",
        example = "서울특별시 강남구 테헤란로 123 ABC빌딩 1층"
    )
    String pickupAddress,

    @Schema(
        description = "배달(도착) 주소",
        example = "서울특별시 송파구 올림픽로 456 DEF아파트 101동 1001호",
        requiredMode = REQUIRED
    )
    String destAddress,

    @Schema(description = "배달 목적지 변경 횟수", example = "1", minimum = "0")
    int changeCount,

    @Schema(description = "결제일")
    LocalDateTime paidAt,

    @Schema(description = "주문 수락일")
    LocalDateTime acceptedAt,

    @Schema(description = "픽업일")
    LocalDateTime pickedUpAt,

    @Schema(description = "배달 완료일")
    LocalDateTime deliveredAt,

    @Schema(description = "생성일")
    LocalDateTime createdAt,

    @Schema(description = "수정일")
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
