package com.example.Jerseysweden.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {

    private String productId;
    private int quantity;
    private double priceAtPurchase;

    // Konstruktor
    public OrderItem(String productId, int quantity, double priceAtPurchase) {
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

}
