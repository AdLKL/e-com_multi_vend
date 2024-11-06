package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByEmail(String email);
}
