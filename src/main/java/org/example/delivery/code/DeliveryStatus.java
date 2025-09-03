package org.example.delivery.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryStatus {

    PENDING("PENDING", "주문이 접수 대기 중입니다(결제 전)."),

    PAID("PAID", "결제가 승인되었습니다."),

    ACCEPTED("ACCEPTED", "가게가 주문을 수락했습니다."),

    PREPARING("PREPARING", "준비 중입니다."),

    ASSIGNED("ASSIGNED", "라이더가 배정되었습니다."),

    DISPATCHING("DISPATCHING", "라이더가 픽업지로 이동 중입니다."),

    PICKED_UP("PICKED_UP", "라이더가 픽업을 완료했습니다."),

    DELIVERED("DELIVERED", "배달이 완료되었습니다."),

    CANCELED("CANCELED", "주문이 취소되었습니다."),

    REFUNDED("REFUNDED", "결제가 환불되었습니다.");

    private final String code;
    private final String description;

}
