package com.ad.ecommerceMultivBackend.controller;

import com.ad.ecommerceMultivBackend.domain.PaymentMethod;
import com.ad.ecommerceMultivBackend.model.*;
import com.ad.ecommerceMultivBackend.repository.SellerReportRepository;
import com.ad.ecommerceMultivBackend.response.PaymentLinkResponse;
import com.ad.ecommerceMultivBackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;

    @PostMapping
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestBody Address shippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartService.findUserCart(user);
        Set<Order> orders = orderService.createOrder(user, shippingAddress, cart);

        //PaymentOrder paymentOrder = paymentService.createOrder(user, orders);

        PaymentLinkResponse res = new PaymentLinkResponse();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistoryHandler(@RequestHeader("Authorization") String jwt)
        throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Order> orders = orderService.userOrderHistory(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt)
        throws Exception {
        User user = userService.findUserByJwt(jwt);
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

    @GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long orderItemId, @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJwt(jwt);
        OrderItem orderItem = orderService.getOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId,
                                             @RequestHeader("Authorization") String jwt)
        throws Exception {
        User user = userService.findUserByJwt(jwt);
        Order order = orderService.cancelOrder(orderId, user);

        Seller seller = sellerService.getSellerById(order.getSellerId());
        SellerReport report = sellerReportService.getSellerReport(seller);
        report.setCanceledOrders(report.getCanceledOrders()+1);
        report.setTotalRefunds(report.getTotalRefunds()+order.getTotalSellingPrice());
        sellerReportService.updateSellerReport(report);

        return ResponseEntity.ok(order);
    }
}
