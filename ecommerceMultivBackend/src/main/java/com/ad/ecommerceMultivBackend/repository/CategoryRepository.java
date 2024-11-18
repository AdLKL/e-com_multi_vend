package com.ad.ecommerceMultivBackend.repository;

import com.ad.ecommerceMultivBackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryId(String categoryId);
}
