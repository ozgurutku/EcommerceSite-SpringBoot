package com.ecommerce.controller;

import com.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
public class ProductController {

    @Inject
    private ProductService productService;

    //Todo: flashattribute,category ?
    @PostMapping
    public String search(@RequestParam("search") String search, Model model){
        model.addAttribute("products", productService.findProducts(search));
        return "resultProduct.html";
    }

    @GetMapping
    public String product(@RequestParam("id") String id, Model model){
        model.addAttribute("productDetail", productService.findProductById(id));
        return "productDetails.html";
    }

    @GetMapping
    public String category(@RequestParam("category") String category, Model model){
        model.addAttribute("category", productService.getCategory(category));
        return "productCategory.html";
    }
}
