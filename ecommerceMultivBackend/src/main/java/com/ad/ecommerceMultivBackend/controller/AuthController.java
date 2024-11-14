package com.ad.ecommerceMultivBackend.controller;

import com.ad.ecommerceMultivBackend.domain.USER_ROLE;
import com.ad.ecommerceMultivBackend.request.LoginOtpRequest;
import com.ad.ecommerceMultivBackend.request.LoginRequest;
import com.ad.ecommerceMultivBackend.request.SignupRequest;
import com.ad.ecommerceMultivBackend.response.ApiResponse;
import com.ad.ecommerceMultivBackend.response.AuthResponse;
import com.ad.ecommerceMultivBackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {
        String jwt = authService.createUser(req);
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("Registered Successfully");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sendLoginOtp(req.getEmail(), req.getRole());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Otp sent successfully");

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {
        AuthResponse res = authService.login(req);
        return ResponseEntity.ok(res);
    }
}
