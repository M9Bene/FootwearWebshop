package com.flashforward.backendspring.model;

import jakarta.persistence.*;

@Entity
public class SizeAndQuantity {

    public SizeAndQuantity() {
    }

    public SizeAndQuantity(int size, int quantity) {
        this.size = size;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    private Shoe shoe;

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }
}
