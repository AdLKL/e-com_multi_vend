package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.domain.AccountStatus;
import com.ad.ecommerceMultivBackend.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);

    List<Seller> findByAccountStatus(AccountStatus accountStatus);
}
