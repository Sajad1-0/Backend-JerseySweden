package com.example.Jerseysweden.model;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {

    @NotNull(message = "Product must have Id")
    private String productId;

    @NotNull(message = "Stock quantity must be positive")
    @Min(value = 1, message = "You must choose at least one product")
    private int quantity;

    @NotNull(message = "Price at purchase must be positive")
    @Min(value = 0)
    private double priceAtPurchase;

    // Konstruktor
    public OrderItem(String productId, int quantity, double priceAtPurchase) {
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

}
