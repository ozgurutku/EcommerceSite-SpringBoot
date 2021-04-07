package com.ecommerce.service.impl;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException("Product not found id:" + id);
        }
        return product;
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
        Product oldProduct = getProduct(id);
        oldProduct.setName(updateProduct.getName());
        oldProduct.setDescription(updateProduct.getDescription());
        oldProduct.setCategory(updateProduct.getCategory());
        oldProduct.setPrice(updateProduct.getPrice());
        saveProduct(updateProduct);
    }
}
