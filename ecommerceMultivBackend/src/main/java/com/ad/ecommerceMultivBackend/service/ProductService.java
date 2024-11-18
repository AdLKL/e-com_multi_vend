package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.exception.ProductException;
import com.ad.ecommerceMultivBackend.model.Product;
import com.ad.ecommerceMultivBackend.model.Seller;
import com.ad.ecommerceMultivBackend.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest req, Seller seller);

    void deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product product) throws ProductException;

    Product findProductById(Long productId) throws ProductException;

    List<Product> searchProducts(String query);

    Page<Product> getAllProducts(
            String category, String brand, String color,
            String sizes, Integer minPrice, Integer maxPrice,
            Integer minDiscount, String sort, String stock,
            Integer pageNumber
    );

    List<Product> getProductBySellerId(Long sellerId);

}
