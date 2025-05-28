package com.example.Jerseysweden.controller;

import com.example.Jerseysweden.dto.CreateProductDTO;
import com.example.Jerseysweden.dto.ProductUpdateDTO;
import com.example.Jerseysweden.service.ProductService;
import com.example.Jerseysweden.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // get product with id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // create product
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductDTO dto) {
        Product created = productService.createProduct(dto);

        return ResponseEntity.status(201).body(created);
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductUpdateDTO dto) throws IllegalAccessException {
        Product updated = productService.updateProduct(id, dto);

        return ResponseEntity.ok(updated);
    }

    // delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }

    // Filteer products by league category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByLeagueCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductByLeagueCategory(category));
    }

}
