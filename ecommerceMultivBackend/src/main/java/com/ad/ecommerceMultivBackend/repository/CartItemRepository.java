package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.Cart;
import com.ad.ecommerceMultivBackend.model.CartItem;
import com.ad.ecommerceMultivBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
