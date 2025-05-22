package com.example.JerseySweden.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// Använder lombok för att automatiskt genererar getters och setters för klassen
@Getter
@Setter
public class Product {

        private String id;
        private String name;
        private String description;
        private double price;
        private String imageUrl;
        private String category;
        private int stock;


        // konstruktor
        public Product(String name, String description, double price, String imageUrl, String category, int stock) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.description = description;
            this.price = price;
            this.imageUrl = imageUrl;
            this.category = category;
            this.stock = stock;
        }

}
