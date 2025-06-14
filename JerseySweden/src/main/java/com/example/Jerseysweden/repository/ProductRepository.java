package com.example.Jerseysweden.repository;


import com.example.Jerseysweden.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.*;

@Component
public class ProductRepository {
    private final Map<String, Product> productMap = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    // Initialisering metod
    public void init() {
        try {

        InputStream inputStream = getClass().getResourceAsStream("/products.json");
        List<Product> products = objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});

        for (Product product : products) {
            // Generera UUID om det inte finns
                if (product.getId() == null || product.getId().isEmpty()) {
                    product.setId(UUID.randomUUID().toString());
                }
                productMap.put(product.getId(), product);
            }
        } catch (Exception e) {
            System.err.println("Couldn't get products" + e);
        }

    }

    public void exportProductToJson(String filename) {
        try {
            objectMapper.writeValue(new File("src/main/resources/" + filename), findAll());
            System.out.println("Produkter exporterade till " + filename);
        } catch (IOException e) {
            System.err.println("Kunde inte exportera produkter: " + e.getMessage());
        }
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    // find by id
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productMap.get(id));
    }


    // find by league category
    public List<Product> findByLeagueCategory(String category) {
        return productMap.values().stream()
                .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    // skapa och uppdatera
    public Product save(Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
        productMap.put(product.getId(), product);
        exportProductToJson("products.json");
        return product;
    }

    // Ta bort product
    public void deleteById(String id) {
        productMap.remove(id);
    }

}
