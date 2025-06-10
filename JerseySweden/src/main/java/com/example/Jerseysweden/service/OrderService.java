package com.example.Jerseysweden.service;
import com.example.Jerseysweden.dto.OrderRequestDto;
import com.example.Jerseysweden.dto.OrderResponseDTO;
import com.example.Jerseysweden.exception.*;
import com.example.Jerseysweden.model.*;
import com.example.Jerseysweden.repository.OrderRepository;
import com.example.Jerseysweden.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;


@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final Map<String, Order> orderStorage = new HashMap<>();
    @Autowired
    public OrderRepository orderRepository;

    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public OrderResponseDTO createOrder(OrderRequestDto request) {

        List<OrderItem> validateItem = new ArrayList<>();
        double total = 0.0;

        for (OrderItem item: request.getItems()) {
            Product product = productRepository.findById(item.getProductId()).orElseThrow(
                    () -> new ProductNotFoundException("Product with Id: " + item.getProductId() + " not found")
            );

            if (product.getStock() < item.getQuantity()) {
                throw new InsufficientStockException("Not enough stock for product: " + product.getName());
            }

            // Update stock
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            // Total amount
            item.setPriceAtPurchase(product.getPrice());
            validateItem.add(item);
            total += product.getPrice() * item.getQuantity();
        }

        Order order = new Order(
                request.getCustomerInfo(),
                validateItem,
                total,
                LocalDateTime.now()
        );

        orderStorage.put(order.getId(), order);
        orderRepository.exportOrders(new ArrayList<>(orderStorage.values()), "orders.json");

        return new OrderResponseDTO(order.getId(), "Order placed Successfully");

    }

   public List<Order> getAllOrders() {
        return new ArrayList<>(orderStorage.values());
   }

   public Order findOrderById(String id) {
        Order order = orderStorage.get(id);

        if (order == null) {
            throw new OrderNotFoundException("Order with Id: " + id + " not found");
        }

        return order;
   }

   public void deleteOrderById(String id) {

        Order order = orderStorage.get(id);

        if (order == null) {
            throw new OrderNotFoundException("Order with Id: " + id + " not found");
        }

        // Återställ lagret
        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product with id: " + item.getProductId() +
                            " not found"));

            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }

        orderStorage.remove(id);
   }

   // Läser in order.json vid start
    @PostConstruct
    public void importAllOrderFromFile() {
        List<Order> importedOrders = orderRepository.importOrders("orders.json");

        for (Order order : importedOrders) {
            orderStorage.put(order.getId(), order);
        }

    }

    // Spara orders.json när appen stängs
    @PreDestroy
    public void exportOrdersToFile() {
        orderRepository.exportOrders(orderStorage.values(), "orders.json");
    }
}
