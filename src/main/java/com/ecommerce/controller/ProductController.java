package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller()
public class ProductController {

    @Inject
    private ProductService productService;

    @GetMapping("/product/add")
    public String addProduct(Model model, @ModelAttribute("product") Product product){
        productService.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/product/delete")
    public String deleteProduct(Model model, @ModelAttribute("product") Product product){
        productService.deleteProduct(product);
        return "redirect:/";
    }

    @GetMapping("/product/update")
    public String updateProduct(Model model, @ModelAttribute("product") Product updateProduct, @RequestParam("id") long id) {
        productService.updateProduct(id,updateProduct);
        return "redirect:/";
    }
}
