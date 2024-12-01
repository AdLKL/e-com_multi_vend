package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
