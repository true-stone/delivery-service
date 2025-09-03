package org.example.delivery.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Comment("배달 주소 히스토리")
@Table(name = "delivery_address_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class DeliveryAddressHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("배달 주소 히스토리 내부 식별자")
    private Long id;

    @Column(nullable = false)
    @Comment("이전 배달 목적지 주소지")
    private String oldDestAddress;

    @Column(nullable = false)
    @Comment("변경 배달 목적지 주소지")
    private String newDestAddress;

    @Comment("주소지 변경 사유")
    private String reason;

    @Comment("주소지 변경 사용자 식별자")
    private Long changedBy;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    @Comment("생성 일자")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private DeliveryOrder order;

    @Builder
    public DeliveryAddressHistory(String oldDestAddress, String newDestAddress, Long changedBy, LocalDateTime createdAt, DeliveryOrder order) {
        this.oldDestAddress = oldDestAddress;
        this.newDestAddress = newDestAddress;
        this.changedBy = changedBy;
        this.createdAt = createdAt;
        this.order = order;
    }
}
