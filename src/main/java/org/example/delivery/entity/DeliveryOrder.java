package org.example.delivery.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.delivery.code.DeliveryStatus;
import org.example.delivery.common.response.code.ErrorCode;
import org.example.delivery.exception.BusinessException;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Comment("배달 주문 정보")
@Table(name = "delivery_order")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryOrder extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("배달 주문 정보 내부 식별자")
    private Long id;

    @Column(nullable = false, unique = true, length = 36)
    @Comment("주문 아이디")
    private String orderUid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("배달 주문 상태")
    private DeliveryStatus status = DeliveryStatus.PENDING;

    @Column(nullable = false)
    @Comment("픽업 주소지")
    private String pickupAddress;

    @Column(nullable = false)
    @Comment("배달 주소지")
    private String destAddress;

    // TODO 픽업/목적지 위도 경도

    @Column(nullable = false)
    @Comment("배달 주소지 변경 횟수")
    private int changeCount = 0;

    @Comment("결제일")
    private LocalDateTime paidAt;

    @Comment("주문 수락일")
    private LocalDateTime acceptedAt;

    @Comment("배달 픽업일")
    private LocalDateTime pickedUpAt;

    @Comment("배달 완료일")
    private LocalDateTime deliveredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Version
    @Column(nullable = false)
    private Long version;

    @Builder
    public DeliveryOrder(String orderUid, DeliveryStatus status, String pickupAddress, String destAddress, int changeCount, LocalDateTime paidAt, LocalDateTime acceptedAt, LocalDateTime pickedUpAt, LocalDateTime deliveredAt, User user) {
        this.orderUid = orderUid;
        this.status = status;
        this.pickupAddress = pickupAddress;
        this.destAddress = destAddress;
        this.changeCount = changeCount;
        this.paidAt = paidAt;
        this.acceptedAt = acceptedAt;
        this.pickedUpAt = pickedUpAt;
        this.deliveredAt = deliveredAt;
        this.user = user;
    }

    /**
     * 목적지 변경 가능 여부
     */
    public boolean isDestinationChangeable() {
        // 픽업 이전까지만 허용
        return switch (status) {
            case PENDING, PAID, ACCEPTED, PREPARING, ASSIGNED, DISPATCHING -> true;
            default -> false;
        };
    }

    public void changeDestination(String newAddress) {
        if (!isDestinationChangeable()) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "변경이 불가능한 상태입니다.");
        }
        if (newAddress == null || newAddress.isBlank()) {
            throw new IllegalArgumentException("목적지 주소는 필수 입니다.");
        }
        this.destAddress = newAddress;
        this.changeCount += 1;
    }

}
