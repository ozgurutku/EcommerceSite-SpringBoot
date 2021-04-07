package com.ecommerce.service;

import com.ecommerce.model.Product;

import java.util.List;

public interface ProductService{

    List<Product> getAllProduct();

    Product getProduct(long id);

    void saveProduct(Product product);

    void deleteProduct(Product product);

    void updateProduct(long id, Product updateProduct);

}
