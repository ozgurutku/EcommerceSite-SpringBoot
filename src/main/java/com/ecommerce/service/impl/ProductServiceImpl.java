package com.ecommerce.service.impl;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProducts(String input) {
        return productRepository.findProductByName(input);
    }

    @Override
    public Product findProductById(long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void updateProduct(long id, Product updateProduct) {
        Product oldProduct = productRepository.findProductById(id);
        oldProduct.setName(updateProduct.getName());
        oldProduct.setDescription(updateProduct.getDescription());
        oldProduct.setCategory(updateProduct.getCategory());
        oldProduct.setPrice(updateProduct.getPrice());
        saveProduct(updateProduct);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findProductByCategory(category);
    }

    @Override
    public List<String> getCategory() {
        return productRepository.findDistinctByCategory();
    }

}
