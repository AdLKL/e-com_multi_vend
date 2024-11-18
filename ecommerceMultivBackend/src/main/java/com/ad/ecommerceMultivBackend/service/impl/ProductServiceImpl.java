package com.ad.ecommerceMultivBackend.service.impl;

import com.ad.ecommerceMultivBackend.exception.ProductException;
import com.ad.ecommerceMultivBackend.model.Category;
import com.ad.ecommerceMultivBackend.model.Product;
import com.ad.ecommerceMultivBackend.model.Seller;
import com.ad.ecommerceMultivBackend.repository.CategoryRepository;
import com.ad.ecommerceMultivBackend.repository.ProductRepository;
import com.ad.ecommerceMultivBackend.request.CreateProductRequest;
import com.ad.ecommerceMultivBackend.service.ProductService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest req, Seller seller) {
        Category category1 = categoryRepository.findByCategoryId(req.getCategory());
        if(category1 == null) {
            Category newCategory = new Category();
            newCategory.setCategoryId(req.getCategory());
            newCategory.setLevel(1);
            category1 = categoryRepository.save(newCategory);
        }
        Category category2 = categoryRepository.findByCategoryId(req.getCategory2());
        if(category2 == null) {
            Category newCategory = new Category();
            newCategory.setCategoryId(req.getCategory2());
            newCategory.setLevel(2);
            newCategory.setParentCategory(category1);
            category2 = categoryRepository.save(newCategory);
        }
        Category category3 = categoryRepository.findByCategoryId(req.getCategory3());
        if(category3 == null) {
            Category newCategory = new Category();
            newCategory.setCategoryId(req.getCategory3());
            newCategory.setLevel(3);
            newCategory.setParentCategory(category2);
            category3 = categoryRepository.save(newCategory);
        }
        int discountPercentage = calculateDiscountPercentage(req.getMrpPrice(), req.getSellingPrice());

        Product product = new Product();
        product.setSeller(seller);
        product.setCategory(category3);
        product.setDescription(req.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setSellingPrice(req.getSellingPrice());
        product.setImages(req.getImages());
        product.setMrpPrice(req.getMrpPrice());
        product.setSizes(req.getSizes());
        product.setDiscountPercent(discountPercentage);

        return productRepository.save(product);
    }

    private int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {
        if(mrpPrice <= 0) throw new IllegalArgumentException("MrpPrice must be greater than 0");
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice)*100;

        return (int)discountPercentage;
    }

    @Override
    public void deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        findProductById(productId);
        product.setId(productId);
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        return productRepository.findById(productId).orElseThrow(
                () -> new ProductException("Product not found with id " + productId)
        );
    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.searchProduct(query);
    }

    @Override
    public Page<Product> getAllProducts(String category, String brand, String color, String sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber) {
        Specification<Product> specification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(category != null) {
                Join<Product, Category> categoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"), category));
            }
            if(color != null) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("color")), color.toLowerCase()));
            }
            if(sizes != null && !sizes.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("size"), sizes));
            }
            if(minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
            }
            if(maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
            }
            if(minDiscount != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercentage"), minDiscount));
            }
            if(stock != null) {
                predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Pageable pageable;
        if(sort != null && !sort.isEmpty()) {
            pageable = switch (sort) {
                case "price_low" ->
                        PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("sellingPrice").ascending());
                case "price_high" ->
                        PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("sellingPrice").descending());
                default -> PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.unsorted());
            };
        } else {
            pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.unsorted());
        }
        return productRepository.findAll(specification, pageable);
    }

    @Override
    public List<Product> getProductBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
}
