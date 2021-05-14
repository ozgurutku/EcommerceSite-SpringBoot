package com.ecommerce.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToMany
    private List<UserProduct> userProducts = new ArrayList<>();

    @OneToOne
    private User user;

    public List<UserProduct> getUserProducts() {
        return userProducts;
    }

    public void setProduct(List<UserProduct> userProducts) {
        this.userProducts = userProducts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
