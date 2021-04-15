package com.ecommerce.controller;

import com.ecommerce.security.AuthenticationFacade;
import com.ecommerce.service.CartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller("/cart/")
public class CartController {

    @Inject
    private CartService cartService;

    @Inject
    private AuthenticationFacade authenticationFacade;

    @GetMapping("cart/all")
    public String getMyProduct(Model model) {
        model.addAttribute("listProduct",cartService.getMyProducts(authenticationFacade.getAuthentication()));
        return "layout.html";
    }

    @GetMapping("cart/add")
    public String addProductToMyCart(Model model, @RequestParam("id") long id) {
        cartService.addProductMyCart(authenticationFacade.getAuthentication(),id);
        return "redirect:/all";
    }

    @GetMapping("cart/delete")
    public String deleteProductToMyCart(Model model, @RequestParam("id") long id) {
        cartService.deleteProductMyCart(authenticationFacade.getAuthentication(),id);
        return "redirect:/all";
    }
}
