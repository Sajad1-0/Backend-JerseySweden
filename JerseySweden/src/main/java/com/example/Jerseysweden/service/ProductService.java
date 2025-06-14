package com.example.Jerseysweden.service;

import com.example.Jerseysweden.dto.CreateProductDTO;
import com.example.Jerseysweden.dto.ProductUpdateDTO;
import com.example.Jerseysweden.exception.ProductNotFoundException;
import com.example.Jerseysweden.mapper.ProductMapper;
import com.example.Jerseysweden.model.Category;
import com.example.Jerseysweden.model.Product;
import com.example.Jerseysweden.repository.CategoryRepository;
import com.example.Jerseysweden.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by id
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product with id: " + id + " not found"
                ));
    }

    // Filter leagues by category
    public List<Product> getProductByLeagueCategory(String category) {
        List<Product> filtered = productRepository.findByLeagueCategory(category);
        if (filtered.isEmpty()) {
            throw new ProductNotFoundException("No products has been found in this league-category: "
                    + category);
        }
        return filtered;
    }

    // Create product with validation
    public Product createProduct(CreateProductDTO dto) {

        Product product = productMapper.toEntity(dto);

        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }

        if (product.getCategory() != null) {
            Category category = categoryRepository.getLeagueByCategoryName(product.getCategory());
            if (category != null) {
                product.setCategoryImageUrl(category.getImageUrl());
            }
        }

        return productRepository.save(product);
    }

    // delete product
    public void deleteProductById(String id) {
        if(productRepository.findById(id).isEmpty()) {
            throw new ProductNotFoundException("Can't remove product - Product with id: "
            + id + " not found");
        }
    }

    // Update product
    public Product updateProduct(String id, ProductUpdateDTO dto) {

        Product existing = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with id: " + id + " not found")
        );


        // Update fields
        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
        if (dto.getImageUrl() != null) existing.setCategory(dto.getCategory());
        if (dto.getPrice() != null) existing.setPrice(dto.getPrice());
        if (dto.getCategory() != null) existing.setCategory(dto.getCategory());
        if (dto.getCategoryImageUrl() != null) existing.setCategoryImageUrl(dto.getCategoryImageUrl());
        if (dto.getStock() != null) existing.setStock(dto.getStock());


        return productRepository.save(existing);
    }
}
