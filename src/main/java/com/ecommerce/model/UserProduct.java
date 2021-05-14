package com.ecommerce.model;

import javax.persistence.*;

@Entity
public class UserProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;

    private int piece;

    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    public UserProduct(Long id, String name, String description, String category, int piece, double price, Image image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.piece = piece;
        this.price = price;
        this.image = image;
    }

    public UserProduct() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }
}
