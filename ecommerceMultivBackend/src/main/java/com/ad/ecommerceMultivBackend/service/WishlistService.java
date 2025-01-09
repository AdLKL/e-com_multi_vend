package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.model.Product;
import com.ad.ecommerceMultivBackend.model.User;
import com.ad.ecommerceMultivBackend.model.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);
}
