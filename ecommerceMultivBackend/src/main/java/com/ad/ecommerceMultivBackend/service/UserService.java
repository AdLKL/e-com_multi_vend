package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.model.User;

public interface UserService {
    User findUserByJwt(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;
}
