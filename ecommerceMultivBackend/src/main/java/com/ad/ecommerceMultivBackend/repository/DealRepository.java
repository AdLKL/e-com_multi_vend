package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
