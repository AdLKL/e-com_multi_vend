package com.ad.ecommerceMultivBackend.controller;

import com.ad.ecommerceMultivBackend.model.Cart;
import com.ad.ecommerceMultivBackend.model.CartItem;
import com.ad.ecommerceMultivBackend.model.Product;
import com.ad.ecommerceMultivBackend.model.User;
import com.ad.ecommerceMultivBackend.request.AddItemRequest;
import com.ad.ecommerceMultivBackend.response.ApiResponse;
import com.ad.ecommerceMultivBackend.service.CartItemService;
import com.ad.ecommerceMultivBackend.service.CartService;
import com.ad.ecommerceMultivBackend.service.ProductService;
import com.ad.ecommerceMultivBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartService.findUserCart(user);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItem item = cartService.addCartItem(user, product, req.getSize(), req.getQuantity());

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable Long cartItemId,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse("Item Deleted Successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(@PathVariable Long cartItemId,
                                                          @RequestBody CartItem cartItem,
                                                          @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        CartItem updatedCartItem = null;
        if(cartItem.getQuantity()>0) {
            updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        }
        return ResponseEntity.ok(updatedCartItem);
    }
}
