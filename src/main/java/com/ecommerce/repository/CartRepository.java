package com.ecommerce.repository;

import com.ecommerce.model.Cart;
import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUser(User user);

    @Transactional
    @Modifying
    @Query("UPDATE UserProduct p set p.piece = p.piece-1 WHERE p.id=:id")
    void updatePiece(@Param("id") long id);
}
