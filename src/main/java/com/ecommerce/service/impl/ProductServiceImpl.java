package com.ecommerce.service.impl;

import com.ecommerce.model.Image;
import com.ecommerce.model.Product;
import com.ecommerce.model.UserProduct;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserProductRepository;
import com.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private UserProductRepository userProductRepository;

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

    //Todo : düzenlenmeli
    @Override
    public void updateProduct(long id, Product updateProduct, Image image) {
        Product oldProduct = productRepository.findProductById(id);
        UserProduct userProduct = userProductRepository.findProductById(id);
        userProduct.setName(updateProduct.getName());
        userProduct.setDescription(updateProduct.getDescription());
        userProduct.setCategory(updateProduct.getCategory());
        userProduct.setPrice(updateProduct.getPrice());
        userProduct.setImage(image);
        oldProduct.setName(updateProduct.getName());
        oldProduct.setDescription(updateProduct.getDescription());
        oldProduct.setCategory(updateProduct.getCategory());
        oldProduct.setPrice(updateProduct.getPrice());
        oldProduct.setImage(image);
        userProductRepository.save(userProduct);
        saveProduct(oldProduct);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findProductByCategory(category);
    }

    @Override
    public List<String> getCategory() {
        return productRepository.findDistinctByCategory();
    }

    public void saveImage(MultipartFile file) throws IOException {
        String myExternalFilePath = System.getProperty("user.dir") + "/images/" + file.getOriginalFilename();
        File convFile = new File(myExternalFilePath);//burası
        //File convFile = new File(fileName);
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
    }

}
