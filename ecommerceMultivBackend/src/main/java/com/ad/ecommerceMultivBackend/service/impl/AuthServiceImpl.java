package com.ad.ecommerceMultivBackend.service.impl;

import com.ad.ecommerceMultivBackend.config.JwtProvider;
import com.ad.ecommerceMultivBackend.domain.USER_ROLE;
import com.ad.ecommerceMultivBackend.model.Cart;
import com.ad.ecommerceMultivBackend.model.Seller;
import com.ad.ecommerceMultivBackend.model.User;
import com.ad.ecommerceMultivBackend.model.VerificationCode;
import com.ad.ecommerceMultivBackend.repository.CartRepository;
import com.ad.ecommerceMultivBackend.repository.SellerRepository;
import com.ad.ecommerceMultivBackend.repository.UserRepository;
import com.ad.ecommerceMultivBackend.repository.VerificationCodeRepository;
import com.ad.ecommerceMultivBackend.request.LoginRequest;
import com.ad.ecommerceMultivBackend.request.SignupRequest;
import com.ad.ecommerceMultivBackend.response.AuthResponse;
import com.ad.ecommerceMultivBackend.service.AuthService;
import com.ad.ecommerceMultivBackend.service.EmailService;
import com.ad.ecommerceMultivBackend.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserServiceImpl;
    private final SellerRepository sellerRepository;

    @Override
    public void sendLoginOtp(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signing_";

        if(email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());
            if(role.equals(USER_ROLE.ROLE_SELLER)) {
                Seller seller = sellerRepository.findByEmail(email);
                if(seller == null) {
                    throw new Exception("Seller isn't found");
                }
            } else {
                User user = userRepository.findByEmail(email);
                if(user == null) {
                    throw new Exception("User doesn't exist with this email");
                }
            }
        }

        VerificationCode verificationCodeExists = verificationCodeRepository.findByEmail(email);
        if(verificationCodeExists != null) {
            verificationCodeRepository.delete(verificationCodeExists);
        }
        String otp = OtpUtil.generateOtp();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setOtp(otp);
        verificationCodeRepository.save(verificationCode);
        String subject = "Multi-vendor E-commerce login OTP";
        String text = "Your login otp is - " + otp;

        emailService.sendVerificationOtpEmail(email, otp, subject, text);
    }

    @Override
    public String createUser(SignupRequest req) throws Exception {
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());

        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
            throw new Exception("Wrong otp...");
        }
        User user = userRepository.findByEmail(req.getEmail());
        if (user == null) {
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("0600000066");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));
            user = userRepository.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse login(LoginRequest req) throws Exception {
        String email = req.getEmail();
        String otp = req.getOtp();

        Authentication authentication = authenticate(email, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Successful");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return authResponse;
    }

    private Authentication authenticate(String email, String otp) throws Exception {
        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(email);

        String SELLER_PREFIX = "seller_";
        if(email.startsWith(SELLER_PREFIX)){
            email = email.substring(SELLER_PREFIX.length());
        }

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid Username or Password");
        }
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
        if(verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("Wrong OTP");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
