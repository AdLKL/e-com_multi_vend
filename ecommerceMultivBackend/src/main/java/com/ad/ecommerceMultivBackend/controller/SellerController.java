package com.ad.ecommerceMultivBackend.controller;

import com.ad.ecommerceMultivBackend.config.JwtProvider;
import com.ad.ecommerceMultivBackend.domain.AccountStatus;
import com.ad.ecommerceMultivBackend.model.Seller;
import com.ad.ecommerceMultivBackend.model.SellerReport;
import com.ad.ecommerceMultivBackend.model.VerificationCode;
import com.ad.ecommerceMultivBackend.repository.VerificationCodeRepository;
import com.ad.ecommerceMultivBackend.request.LoginRequest;
import com.ad.ecommerceMultivBackend.response.AuthResponse;
import com.ad.ecommerceMultivBackend.service.AuthService;
import com.ad.ecommerceMultivBackend.service.EmailService;
import com.ad.ecommerceMultivBackend.service.SellerReportService;
import com.ad.ecommerceMultivBackend.service.SellerService;
import com.ad.ecommerceMultivBackend.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
    private final AuthService authService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final SellerReportService sellerReportService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();

        req.setEmail("seller_" + email);

        AuthResponse authResponse = authService.login(req);
        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {
        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("Wrong Otp!");
        }
        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);
        return ResponseEntity.ok(seller);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {
        Seller savedSeller = sellerService.createSeller(seller);
        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(seller.getEmail());
        verificationCode.setOtp(otp);
        verificationCodeRepository.save(verificationCode);

        String subject = "Email Verification code";
        String text = "Welcome to my project, verify your account using this link ";
        String frontend_url = "http://localhost:3000/verify-seller/";
        emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp(), subject, text+frontend_url);
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws Exception {
        Seller seller = sellerService.getSellerById(id);
        return ResponseEntity.ok(seller);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport> getSellerReport(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);
        SellerReport sellerReport = sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(sellerReport, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(@RequestParam(required = false) AccountStatus status) throws Exception {
        List<Seller> sellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping
    public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt, @RequestBody Seller seller) throws Exception {
        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updatedSeller = sellerService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

}
