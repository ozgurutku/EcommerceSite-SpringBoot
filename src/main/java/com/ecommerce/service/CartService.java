package com.ecommerce.service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.User;

import java.util.List;

public interface CartService {

    Cart getMyCart(User user);
    List getMyProducts(String email);
    void addProductMyCart(String email, long id);
    void deleteProductMyCart(String email, long id);

}
