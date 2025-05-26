package com.example.Jerseysweden.productValidator;

import com.example.Jerseysweden.model.Product;

public class ProductValidator {

    public static void validate(Product product) throws IllegalAccessException {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalAccessException("Product's name required");
        }

        if (product.getPrice() < 0) {
            throw new IllegalAccessException("Price required");
        }

        if (product.getCategory() == null || product.getCategory().isEmpty()) {
            throw new IllegalAccessException("Product category required");
        }

        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new IllegalAccessException("Product description required");
        }

        if (product.getStock() < 0) {
            throw new IllegalAccessException("Products can't have a negative value");
        }

        /*
        if (product.getImageUrl() == null || product.getImageUrl().isEmpty()) {
            throw new IllegalAccessException("Product image required");
        }

         */
    }
}
