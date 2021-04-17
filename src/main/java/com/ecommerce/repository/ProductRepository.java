package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //List<Product> findProductByNameLike(String input); OR
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:input%")
    List<Product> findProductByName(@Param("input") String input);

    Product findProductById(long id);

    @Query("SELECT DISTINCT category FROM Product")
    List<String> findDistinctByCategory();

    List<Product> findProductByCategory(String category);
}
