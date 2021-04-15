package com.ecommerce.service.impl;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    CartRepository cartRepository;

    public Cart getMyCart(User user){
        return cartRepository.findCartByUser(user);
    }

    public List getMyProducts(String email){
        Cart cart = getMyCart(userRepository.findByEmail(email));
        return cart.getProduct();
    }

    public void addProductMyCart(String email, long id){
        Cart cart = getMyCart(userRepository.findByEmail(email));
        Product product = productRepository.findProductById(id);
        cart.getProduct().add(product);
        cartRepository.save(cart);
    }

    public void deleteProductMyCart(String email, long id){
        Cart cart = getMyCart(userRepository.findByEmail(email));
        Product product = productRepository.findProductById(id);
        cart.getProduct().remove(product);
        cartRepository.save(cart);
    }
}
