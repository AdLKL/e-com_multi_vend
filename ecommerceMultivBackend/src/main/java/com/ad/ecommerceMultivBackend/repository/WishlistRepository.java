package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUserId(Long userId);
}
