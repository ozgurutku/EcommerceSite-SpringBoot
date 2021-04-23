package com.ecommerce.controller;

import com.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@RequestMapping("/ecommerce")
public class HomeController {

    @Inject
    ProductService productService;

    @ModelAttribute
    public void addAttribute(Model model){
        model.addAttribute("categories", productService.getCategory());
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("listProduct", productService.getAllProduct());
        return "home.html";
    }

    //Todo: flashattribute,category ?
    @PostMapping("/search")
    public String search(@RequestParam("search") String search, Model model){
        model.addAttribute("listProduct", productService.findProducts(search));
        return "home.html";
    }

    @GetMapping("/productDetails/{id}")
    public String product(@PathVariable("id") long id, Model model){
        model.addAttribute("productDetail", productService.findProductById(id));
        return "productDetails.html";
    }

    @GetMapping("/product/{category}")
    public String category(@PathVariable("category") String category, Model model){
        model.addAttribute("listProduct", productService.getProductByCategory(category));
        return "home.html";
    }
}
