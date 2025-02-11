package com.ad.ecommerceMultivBackend.controller;

import com.ad.ecommerceMultivBackend.model.Home;
import com.ad.ecommerceMultivBackend.model.HomeCategory;
import com.ad.ecommerceMultivBackend.service.HomeCategoryService;
import com.ad.ecommerceMultivBackend.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeCategoryController {
    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;

    @PostMapping("/admin/home/categories")
    public ResponseEntity<Home> createHomeCategories(@RequestBody List<HomeCategory> homeCategories) {
        List<HomeCategory> categories = homeCategoryService.createCategories(homeCategories);
        Home home = homeService.createHomePageData(categories);
        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategory() {
        List<HomeCategory> categories = homeCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/admin/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategory(@PathVariable Long id,
                                                           @RequestBody HomeCategory homeCategory) throws Exception {
        HomeCategory updatedCategory = homeCategoryService.updateHomeCategory(homeCategory, id);
        return ResponseEntity.ok(updatedCategory);
    }
}
