package com.ad.ecommerceMultivBackend.controller;

import com.ad.ecommerceMultivBackend.model.Product;
import com.ad.ecommerceMultivBackend.model.Review;
import com.ad.ecommerceMultivBackend.model.User;
import com.ad.ecommerceMultivBackend.request.CreateReviewRequest;
import com.ad.ecommerceMultivBackend.response.ApiResponse;
import com.ad.ecommerceMultivBackend.service.ProductService;
import com.ad.ecommerceMultivBackend.service.ReviewService;
import com.ad.ecommerceMultivBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/product/{productId}/reviews")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<Review> writeReview(@RequestBody CreateReviewRequest req,
                                              @PathVariable Long productId,
                                              @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Product product = productService.findProductById(productId);
        Review review = reviewService.createReview(req, user, product);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@RequestBody CreateReviewRequest req,
                                               @PathVariable Long reviewId,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Review review = reviewService.updateReview(reviewId, req.getReviewText(), req.getReviewRating(), user.getId());
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId,
                                                    @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        reviewService.deleteReview(reviewId, user.getId());
        ApiResponse res = new ApiResponse();
        res.setMessage("Review deleted successfully");
        return ResponseEntity.ok(res);
    }
}
