package com.example.buoi7java.controller;

import com.example.buoi7java.model.Product;
import com.example.buoi7java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public String home(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Product> products;
        if (keyword != null && !keyword.isEmpty()) {
            products = productService.searchProductsByName(keyword);
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("products", products);
        return "products/product-list";
    }


    public boolean isLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !authentication.getAuthorities().isEmpty();
    }
}
