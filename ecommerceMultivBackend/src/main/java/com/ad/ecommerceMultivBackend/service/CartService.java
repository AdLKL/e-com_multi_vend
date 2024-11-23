package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.model.Cart;
import com.ad.ecommerceMultivBackend.model.CartItem;
import com.ad.ecommerceMultivBackend.model.Product;
import com.ad.ecommerceMultivBackend.model.User;

public interface CartService {
    CartItem addCartItem(
            User user, Product product,
            String size, int quantity
    );

    Cart findUserCart(User user);
}
