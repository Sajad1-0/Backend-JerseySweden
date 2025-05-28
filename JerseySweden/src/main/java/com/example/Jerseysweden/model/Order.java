package com.example.Jerseysweden.model;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class Order {
    private String id;

    @NotNull(message = "Customer info is required")
    private CustomerInfo customerInfo;

    @NotNull(message = "You must at least list a product")
    @Min(value = 1, message = "It must include at least 1 product listed")
    private List<OrderItem> items;

    @NotNull(message = "Total amount must be positive")
    @Min(value = 0)
    private Double totalAmount;

    private LocalDateTime orderDate;

    public Order(CustomerInfo customerInfo, List<OrderItem> items, double totalAmount, LocalDateTime orderDate) {
        this.id = UUID.randomUUID().toString();
        this.customerInfo = customerInfo;
        this.items = items;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public CustomerInfo getCustomerInfo() { return customerInfo; };
    public void setCustomerInfo(CustomerInfo customerInfo) { this.customerInfo = customerInfo;}

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> orderItems) {this.items = items;}

    public double getTotalAmount() {return totalAmount;}
    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    public LocalDateTime getOrderDate() {return orderDate;}
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
}
