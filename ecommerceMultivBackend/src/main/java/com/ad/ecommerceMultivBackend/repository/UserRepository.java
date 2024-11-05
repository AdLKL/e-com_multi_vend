package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
