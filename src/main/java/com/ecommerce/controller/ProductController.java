package com.ecommerce.controller;

import com.ecommerce.model.Image;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;

@Controller()
@RequestMapping("/product")
public class ProductController {

    @Inject
    private ProductService productService;

    @GetMapping("/add")
    public String getAddProductPage(Model model){
        model.addAttribute("product",new Product());
        return "productAdd.html";
    }

    @PostMapping("/add")
    public String addProduct(Model model, @ModelAttribute("product") Product product, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setFileName(multipartFile.getOriginalFilename());
        product.setImage(image);
        productService.saveImage(multipartFile);
        productService.saveProduct(product);
        return "redirect:/ecommerce/home";
    }

    @GetMapping("/delete")
    public String deleteProduct(Model model, @ModelAttribute("product") Product product){
        productService.deleteProduct(product);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateProduct(Model model, @ModelAttribute("product") Product updateProduct, @RequestParam("id") long id) {
        productService.updateProduct(id,updateProduct);
        return "redirect:/";
    }
}
