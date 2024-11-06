package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByEmail(String email);
}
