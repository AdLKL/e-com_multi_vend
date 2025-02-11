package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.model.Home;
import com.ad.ecommerceMultivBackend.model.HomeCategory;

import java.util.List;

public interface HomeService {
    Home createHomePageData(List<HomeCategory> allCategories);
}
