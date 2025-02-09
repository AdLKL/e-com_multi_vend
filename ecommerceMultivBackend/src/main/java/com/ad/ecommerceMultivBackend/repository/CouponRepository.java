package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
