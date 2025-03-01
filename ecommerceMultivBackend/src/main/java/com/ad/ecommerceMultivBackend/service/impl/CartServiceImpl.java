package com.ad.ecommerceMultivBackend.service.impl;

import com.ad.ecommerceMultivBackend.model.Cart;
import com.ad.ecommerceMultivBackend.model.CartItem;
import com.ad.ecommerceMultivBackend.model.Product;
import com.ad.ecommerceMultivBackend.model.User;
import com.ad.ecommerceMultivBackend.repository.CartItemRepository;
import com.ad.ecommerceMultivBackend.repository.CartRepository;
import com.ad.ecommerceMultivBackend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);
        CartItem presentCart = cartItemRepository.findByCartAndProductAndSize(cart, product, size);
        if (presentCart == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setSize(size);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());

            int totalPrice = quantity * product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(quantity * product.getMrpPrice());
            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);
            return cartItemRepository.save(cartItem);
        }
        return presentCart;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());
        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalItem += cartItem.getMrpPrice();
            totalDiscountedPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));
        return cart;
    }

    private int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {
        if(mrpPrice <= 0) return 0;
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice)*100;

        return (int)discountPercentage;
    }

}
