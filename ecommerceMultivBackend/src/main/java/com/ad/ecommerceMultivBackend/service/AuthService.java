package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.request.SignupRequest;

public interface AuthService {
    void sendLoginOtp(String email) throws Exception;
    String createUser(SignupRequest req) throws Exception;
}
