package com.ad.ecommerceMultivBackend.model;

import com.ad.ecommerceMultivBackend.domain.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDetails {
    PaymentStatus paymentStatus;
}
