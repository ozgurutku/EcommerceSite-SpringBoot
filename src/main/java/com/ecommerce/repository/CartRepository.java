package com.ecommerce.repository;

import com.ecommerce.model.Cart;
import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findCartByUser(User user);

    @Transactional
    @Modifying
    @Query("UPDATE Cart p set p.piece = p.piece-1 WHERE p.products.id=:productId and p.user.id=:userId")
    void decrasePiece(Long productId, Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Cart p set p.piece = p.piece+1 WHERE p.products.id=:productId and p.user.id=:userId")
    void increasePiece(Long productId, Long userId);
}
