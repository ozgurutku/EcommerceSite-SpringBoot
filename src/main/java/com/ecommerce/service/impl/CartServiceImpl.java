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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    CartRepository cartRepository;

    public List<Cart> getMyCart(User user){
        return cartRepository.findCartByUser(user);
    }

    public List getMyProducts(String email){
        List<Cart> cart = getMyCart(userRepository.findByEmail(email));
        cart.stream().forEach(cart1 -> cart1.getProducts().setPiece(cart1.getPiece()));
        List<Product> products = cart.stream().map(cart1 -> cart1.getProducts()).collect(Collectors.toList());
        return products ;
    }

    //Todo : düzenlenmeli
    public void addProductMyCart(String email, long id){
        User user = userRepository.findByEmail(email);
        List<Cart> cart = getMyCart(user);
        if(cart == null){
            cart = new ArrayList<>();
        }
        Product product = productRepository.findProductById(id);
        if (product.getPiece() >0) {
            Product products = cart.stream().map(cart1 -> cart1.getProducts()).filter(userProduct1 -> userProduct1.getId() == product.getId()).findAny().orElse(null);
            //List<Product> products = cart.stream().map(cart1 -> cart1.getProducts()).filter(userProduct1 -> userProduct1.getId() == product.getId()).collect(Collectors.toList());
            if (products != null) {
                cartRepository.increasePiece(products.getId(),user.getId());
                productRepository.decreasePiece(product.getId());
            }else {
                Cart cart1 = new Cart();
                cart1.setProducts(product);
                cart1.setUser(user);
                cart1.setPiece(1);
                cartRepository.save(cart1);
                productRepository.decreasePiece(product.getId());
            }
        }else {
            System.out.println("üründen kalmadı");
        }
    }

    public void deleteProductMyCart(String email, long id){
        User user = userRepository.findByEmail(email);
        List<Cart> cart = getMyCart(user);
        Product product = productRepository.findProductById(id);
        Cart carts = cart.stream().filter(cart1 -> cart1.getProducts().getId() == product.getId()).findAny().orElse(null);
        if(carts.getPiece() == 0) {
            System.out.printf("yapcak bişi yok");
        }else {
            cartRepository.decrasePiece(product.getId(),user.getId());
            productRepository.incrementPiece(product.getId());
        }
    }
}
