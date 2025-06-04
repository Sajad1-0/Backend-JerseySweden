package com.example.Jerseysweden.controller;
import com.example.Jerseysweden.exception.OrderNotFoundException;
import com.example.Jerseysweden.service.OrderService;
import com.example.Jerseysweden.dto.OrderRequestDto;
import com.example.Jerseysweden.dto.OrderResponseDTO;
import com.example.Jerseysweden.model.Order;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(@Valid @RequestBody OrderRequestDto request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.findOrderById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String id) {
        orderService.deleteOrderById(id);

        return ResponseEntity.ok("Order with Id: " + id + " deleted successfully");
    }

}
