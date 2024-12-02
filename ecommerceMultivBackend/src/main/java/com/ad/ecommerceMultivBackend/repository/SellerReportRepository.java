package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.SellerReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerReportRepository extends JpaRepository<SellerReport, Long> {
    SellerReport findBySellerId(Long sellerId);
}
