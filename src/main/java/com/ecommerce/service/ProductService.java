package com.ecommerce.service;

import com.ecommerce.model.Product;

import java.util.List;

public interface ProductService{

    List<Product> getAllProduct();

    List<Product> findProducts(String id);

    Product findProductById(String id);

    void saveProduct(Product product);

    void deleteProduct(Product product);

    void updateProduct(long id, Product updateProduct);

    List<Product> getCategory(String category);

}
