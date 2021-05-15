package com.ecommerce.service.impl;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.model.UserProduct;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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

    @Inject
    UserProductRepository userProductRepository;

    public Cart getMyCart(User user){
        return cartRepository.findCartByUser(user);
    }

    public List getMyProducts(String email){
        Cart cart = getMyCart(userRepository.findByEmail(email));
        cart.getUserProducts().stream().filter(userProduct -> userProduct.getPiece() > 0).collect(Collectors.toList());
        return cart.getUserProducts();
    }

    //Todo : düzenlenmeli
    public void addProductMyCart(String email, long id){
        Cart cart = getMyCart(userRepository.findByEmail(email));
        Product product = productRepository.findProductById(id);
        if (product.getPiece() >0) {
            UserProduct userProduct = new UserProduct(product.getId(),product.getName(),
                    product.getDescription(),product.getCategory(),product.getPiece(),product.getPrice(),product.getImage());
            if (userProductRepository.findProductById(userProduct.getId()) != null) {
                userProductRepository.updatePiece(userProduct.getId());
                productRepository.updatePiece(product.getId());
            }else {
                userProduct.setPiece(1);
                cart.getUserProducts().add(userProduct);
                userProductRepository.save(userProduct);
                cartRepository.save(cart);
                productRepository.updatePiece(product.getId());
            }
        }else {
            System.out.println("üründen kalmadı");
        }
    }

    public void deleteProductMyCart(String email, long id){
        Cart cart = getMyCart(userRepository.findByEmail(email));
        UserProduct product = userProductRepository.findProductById(id);
        if(product.getPiece() == 1) {
            cart.getUserProducts().remove(product);
        }
        cartRepository.updatePiece(id);
        cartRepository.save(cart);
    }
}
