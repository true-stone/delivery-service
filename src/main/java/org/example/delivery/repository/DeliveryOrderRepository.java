package org.example.delivery.repository;

import org.example.delivery.entity.DeliveryOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {

    Page<DeliveryOrder> findByUser_IdAndCreatedAtBetweenOrderByCreatedAtDesc(
        Long userId, LocalDateTime from, LocalDateTime to, Pageable pageable
    );

    Optional<DeliveryOrder> findByOrderUidAndUser_Id(String orderUid, Long userId);
}
