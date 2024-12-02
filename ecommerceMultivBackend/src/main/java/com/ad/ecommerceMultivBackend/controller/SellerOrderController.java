package com.ad.ecommerceMultivBackend.controller;

import com.ad.ecommerceMultivBackend.domain.OrderStatus;
import com.ad.ecommerceMultivBackend.model.Order;
import com.ad.ecommerceMultivBackend.model.Seller;
import com.ad.ecommerceMultivBackend.service.OrderService;
import com.ad.ecommerceMultivBackend.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/orders")
public class SellerOrderController {
    private final OrderService orderService;
    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrdersHandler(@RequestHeader("Authorization") String jwt)
        throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);
        List<Order> orders = orderService.sellerOrders(seller.getId());
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrderHandler(@RequestHeader("Authorization") String jwt,
                                                    @PathVariable Long orderId,
                                                    @PathVariable OrderStatus orderStatus) throws Exception {
        Order order = orderService.updateOrderStatus(orderId, orderStatus);
        return ResponseEntity.ok(order);
    }
}
