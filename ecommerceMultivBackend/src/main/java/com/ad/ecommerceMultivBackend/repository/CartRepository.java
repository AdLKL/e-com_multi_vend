package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
