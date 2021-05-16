package com.ecommerce.controller;

import com.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;

@Controller
public class AdminPanelController {

    @Inject
    ProductService productService;

    @GetMapping("/adminPanel")
    public String adminPanel(Model model) {
        model.addAttribute("categories", productService.getCategory());
        return "adminPanel.html";
    }
}
