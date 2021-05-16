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

@Controller
@RequestMapping("/product")
public class ProductController {

    @Inject
    private ProductService productService;

    @ModelAttribute
    public void addAttribute(Model model){
        model.addAttribute("categories", productService.getCategory());
    }

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

    @GetMapping("/update")
    public String getListUpdateProduct(Model model){
        model.addAttribute("listProduct", productService.getAllProduct());
        return "listProductUpdate.html";
    }

    @GetMapping("/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable("id") long id){
        model.addAttribute("productDetail", productService.findProductById(id));
        return "productUpdate.html";
    }

    //Todo : düzenlenmeli
    @PostMapping("/update/{id}")
    public String deleteProduct(Model model, @PathVariable("id") long id, @ModelAttribute("productDetail") Product product, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setFileName(multipartFile.getOriginalFilename());
        productService.saveImage(multipartFile);
        productService.updateProduct(id,product,image);
        return "redirect:/product/update";
    }
}
