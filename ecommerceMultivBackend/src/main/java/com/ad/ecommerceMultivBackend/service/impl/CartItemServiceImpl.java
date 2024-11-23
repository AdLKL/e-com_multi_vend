package com.ad.ecommerceMultivBackend.service.impl;

import com.ad.ecommerceMultivBackend.model.CartItem;
import com.ad.ecommerceMultivBackend.model.User;
import com.ad.ecommerceMultivBackend.repository.CartItemRepository;
import com.ad.ecommerceMultivBackend.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
        CartItem item = findCartItemById(id);
        User cartItemUser = item.getCart().getUser();
        if(cartItemUser.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);
        }
        throw new Exception("You can't update this cartItem");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem cartItem = findCartItemById(cartItemId);
        User cartItemUser = cartItem.getCart().getUser();
        if(cartItemUser.getId().equals(userId)) {
            cartItemRepository.delete(cartItem);
        }
        else {
            throw new Exception("You can't delete this cartItem");
        }
    }

    @Override
    public CartItem findCartItemById(Long id) throws Exception {

        return cartItemRepository.findById(id).orElseThrow(() -> new Exception("CartItem not found with this id"));
    }
}
