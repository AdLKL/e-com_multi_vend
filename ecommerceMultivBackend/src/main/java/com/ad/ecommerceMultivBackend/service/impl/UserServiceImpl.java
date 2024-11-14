package com.ad.ecommerceMultivBackend.service.impl;

import com.ad.ecommerceMultivBackend.config.JwtProvider;
import com.ad.ecommerceMultivBackend.model.User;
import com.ad.ecommerceMultivBackend.repository.UserRepository;
import com.ad.ecommerceMultivBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromToken(jwt);
        return this.findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found with email - " + email);
        }
        return user;
    }
}
