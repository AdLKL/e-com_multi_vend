package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.model.Product;
import com.ad.ecommerceMultivBackend.model.Review;
import com.ad.ecommerceMultivBackend.model.User;
import com.ad.ecommerceMultivBackend.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest req, User user, Product product);
    List<Review> getReviewByProductId(Long productId);
    Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;
    void deleteReview(Long reviewId, Long userId) throws Exception;
    Review getReviewById(Long reviewId) throws Exception;
}
