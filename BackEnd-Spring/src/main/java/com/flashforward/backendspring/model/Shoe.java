package com.flashforward.backendspring.model;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Shoe {


    public Shoe() {
    }

    public Shoe(String name, String brand, double price, String imgUrl, String detailedInfo,
                List<SizeAndQuantity> sizeAndQuantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.imgUrl = imgUrl;
        this.detailedInfo = detailedInfo;
        this.sizeAndQuantity = sizeAndQuantity;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(name = "detailed_info", nullable = false)
    private String detailedInfo;

    @OneToMany(mappedBy = "shoe", cascade = CascadeType.ALL)
    private List<SizeAndQuantity> sizeAndQuantity;


    public String getBrand() {
        return brand;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDetailedInfo() {
        return detailedInfo;
    }

    public List<SizeAndQuantity> getSizeAndQuantity() {
        return sizeAndQuantity;
    }
}