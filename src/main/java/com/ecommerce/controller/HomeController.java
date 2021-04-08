package com.ecommerce.controller;

import com.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;

@Controller
public class HomeController {

    @Inject
    ProductService productService;

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("listProduct", productService.getAllProduct());
        return "home.html";
    }
}
