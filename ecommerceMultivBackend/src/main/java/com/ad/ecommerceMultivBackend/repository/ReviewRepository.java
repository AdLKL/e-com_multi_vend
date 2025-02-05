package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
}
