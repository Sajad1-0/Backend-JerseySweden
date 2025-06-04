package com.example.Jerseysweden.serviceTest;


import com.example.Jerseysweden.dto.CreateProductDTO;
import com.example.Jerseysweden.exception.ProductNotFoundException;
import com.example.Jerseysweden.mapper.ProductMapper;
import com.example.Jerseysweden.model.Product;
import com.example.Jerseysweden.repository.ProductRepository;
import com.example.Jerseysweden.service.CategoryService;
import com.example.Jerseysweden.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private ProductMapper productMapper;
    private ProductService productService;


    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        categoryService = mock(CategoryService.class);
        productMapper = mock(ProductMapper.class);
        productService = new ProductService(productRepository, categoryService);
        productService.productMapper = productMapper;
    }

    @Test
    void getProductById_shouldReturnProduct_whenFound() {
        Product product = new Product();
        product.setId("123");
        when(productRepository.findById("123")).thenReturn(Optional.of(product));

        Product result = productService.getProductById("123");

        assertEquals("123", result.getId());
    }

    @Test
    void getProductById_shouldThrow_whenNotFound() {
        when(productRepository.findById("999")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById("999"));
    }

    @Test
    void createProduct_shouldSaveAndReturnProduct() {
        CreateProductDTO dto = new CreateProductDTO(
                "Fc Barcelona 2025",
                "Officiel kit for season 2025",
                400.00,
                "htttp/Barcelona2025kit",
                "La liga",
                "http%laligla",
                4);
        Product product = new Product();
        when(productMapper.toEntity(dto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(dto);

        assertNotNull(result);
        verify(productRepository).save(product);
    }
}
