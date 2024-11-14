package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.domain.USER_ROLE;
import com.ad.ecommerceMultivBackend.request.LoginRequest;
import com.ad.ecommerceMultivBackend.request.SignupRequest;
import com.ad.ecommerceMultivBackend.response.AuthResponse;

public interface AuthService {
    void sendLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse login(LoginRequest req) throws Exception;
}
