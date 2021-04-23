package com.ecommerce.controller;

import com.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.inject.Inject;

@Controller
public class LoginController {

    @Inject
    ProductService productService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public String accountVerification(Model model) {
        model.addAttribute("categories", productService.getCategory());
        return "home.html";
    }
}
