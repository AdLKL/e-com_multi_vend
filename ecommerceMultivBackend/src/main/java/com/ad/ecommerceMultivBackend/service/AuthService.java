package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.request.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest req);
}
