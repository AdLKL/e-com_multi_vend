package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.model.Order;
import com.ad.ecommerceMultivBackend.model.PaymentOrder;
import com.ad.ecommerceMultivBackend.model.User;
import com.stripe.exception.StripeException;

import java.util.Set;

public interface PaymentService {
    PaymentOrder createOrder(User user, Set<Order> orders);

    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception;

    Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId);

    String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException;
}
