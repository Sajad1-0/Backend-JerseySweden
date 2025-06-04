package com.example.Jerseysweden.serviceTest;

import com.example.Jerseysweden.dto.OrderRequestDto;
import com.example.Jerseysweden.dto.OrderResponseDTO;
import com.example.Jerseysweden.exception.InsufficientStockException;
import com.example.Jerseysweden.model.CustomerInfo;
import com.example.Jerseysweden.model.OrderItem;
import com.example.Jerseysweden.model.Product;
import com.example.Jerseysweden.repository.ProductRepository;
import com.example.Jerseysweden.service.OrderExportImport;
import com.example.Jerseysweden.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private ProductRepository productRepository;
    private OrderExportImport orderExportImport;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        orderExportImport = mock(OrderExportImport.class);
        orderService = new OrderService(productRepository);
        orderService.orderExportImport = orderExportImport;
    }

    @Test
    void createOrder_shouldReturnResponseAndReduceStock() {
        OrderItem item = new OrderItem("product_21", 3, 100);
        Product product = new Product();
        product.setId("product_21");
        product.setName("Kits");
        product.setStock(10);
        product.setPrice(100.00);

        CustomerInfo customerInfo = new CustomerInfo("Sajjad", "koriandergränd 28",
                "sajjad@gmail.com");

        OrderRequestDto requestDto = new OrderRequestDto(customerInfo, List.of(item));

        when(productRepository.findById("product_21")).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        OrderResponseDTO responseDTO = orderService.createOrder(requestDto);

        assertNotNull(responseDTO);
        assertEquals("Order placed Successfully", responseDTO.getMessage());
        assertEquals(7, product.getStock()); // 10 - 3
    }

    @Test
    void createOrder_shouldThrow_whenInsufficientStock() {
        OrderItem item = new OrderItem("product_10", 20, 500);
        Product product = new Product();
        product.setId("product_10");
        product.setName("Kist 2024");
        product.setStock(4);
        product.setPrice(100.00);

        OrderRequestDto requestDto = new OrderRequestDto(new CustomerInfo("Sajjad", "Koriandergränd",
                "Sajjadqaderi@gmail.com"), List.of(item));
        when(productRepository.findById("product_10")).thenReturn(Optional.of(product));

        assertThrows(InsufficientStockException.class, () -> orderService.createOrder(requestDto));
    }
}
