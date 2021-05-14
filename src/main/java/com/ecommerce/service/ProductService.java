package com.ecommerce.service;

import com.ecommerce.model.Image;
import com.ecommerce.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService{

    List<Product> getAllProduct();

    List<Product> findProducts(String id);

    Product findProductById(long id);

    void saveProduct(Product product);

    void updateProduct(long id, Product updateProduct, Image image);

    List<Product> getProductByCategory(String category);

    List<String> getCategory();

    void saveImage(MultipartFile file) throws IOException;

}
