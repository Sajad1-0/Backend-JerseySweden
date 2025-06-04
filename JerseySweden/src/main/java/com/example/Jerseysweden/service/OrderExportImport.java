package com.example.Jerseysweden.service;

import com.example.Jerseysweden.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
public class OrderExportImport {

    private final ObjectMapper mapper;

    public OrderExportImport() {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void exportOrders(Collection<Order> orders, String filePath) {
        try {
            System.out.println("Exporting orders: " + orders.size());

            mapper.writeValue(new File(filePath), orders);
            System.out.println("Orders exported to: " + filePath);
        } catch (IOException e) {
            System.err.println(" Failed to export orders: " + e.getMessage());
        }
    }

    public List<Order> importOrders(String filePath) {
        try {
            return mapper.readValue(new File(filePath), new TypeReference<>() {});
        } catch (IOException e) {
            System.err.println("Failed to import orders: " + e.getMessage());
            return List.of();
        }
    }
}
