package com.example.Jerseysweden.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;

import java.util.UUID;

@Getter
@Setter
public class CreateProductDTO {
    private String id;

    @NotNull(message = "Name is required")
    @Size(min = 4, max = 50, message = "Name must be between 4 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be positive")
    private Double price;

    @NotNull(message = "Image is required")
    private String imageUrl;

    @NotBlank(message = "Category is required")
    private String category;
    private String categoryImageUrl;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stock;

    public CreateProductDTO(String name, String description, Double price, String imageUrl, String category,
                            String categoryImageUrl, Integer stock ) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
        this.categoryImageUrl = categoryImageUrl;
        this.stock = stock;
    }
}
