package com.ecommerce.controller;

import com.ecommerce.security.AuthenticationFacade;
import com.ecommerce.service.CartService;
import com.ecommerce.service.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Inject
    private CartService cartService;

    @Inject
    private ProductService productService;

    @Inject
    private AuthenticationFacade authenticationFacade;

    @ModelAttribute
    public void addAttribute(Model model){
        model.addAttribute("categories", productService.getCategory());
    }

    @GetMapping("/all")
    public String getMyProduct(Model model) {
        model.addAttribute("listMyProduct",cartService.getMyProducts(authenticationFacade.getAuthentication()));
        return "myProduct.html";
    }

    @GetMapping("/add")
    public String addProductToMyCart(Model model, @RequestParam("id") long id) {
        cartService.addProductMyCart(authenticationFacade.getAuthentication(),id);
        return "redirect:/cart/all";
    }

    @GetMapping("/delete")
    public String deleteProductToMyCart(Model model, @RequestParam("id") long id) {
        cartService.deleteProductMyCart(authenticationFacade.getAuthentication(),id);
        return "redirect:/cart/all";
    }
}
