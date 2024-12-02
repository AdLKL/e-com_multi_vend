package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.model.Seller;
import com.ad.ecommerceMultivBackend.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
