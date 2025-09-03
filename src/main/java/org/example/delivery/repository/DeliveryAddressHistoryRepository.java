package org.example.delivery.repository;

import org.example.delivery.entity.DeliveryAddressHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressHistoryRepository extends JpaRepository<DeliveryAddressHistory, Long> {
}
