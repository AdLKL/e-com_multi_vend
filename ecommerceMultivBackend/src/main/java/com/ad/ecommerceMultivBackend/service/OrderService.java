package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.domain.OrderStatus;
import com.ad.ecommerceMultivBackend.model.*;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<Order> createOrder(User user, Address shippingAddress, Cart cart);

    Order findOrderById(long id) throws Exception;

    List<Order> userOrderHistory(Long userId);

    List<Order> sellerOrders(Long sellerId);

    Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception;

    Order cancelOrder(Long orderId, User user) throws Exception;

    OrderItem getOrderItemById(Long id) throws Exception;
}
