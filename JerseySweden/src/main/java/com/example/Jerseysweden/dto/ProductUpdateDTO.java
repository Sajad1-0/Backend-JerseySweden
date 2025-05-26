package com.example.Jerseysweden.dto;


import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDTO {
    private String name;
    private String description;

    @Min(value = 0, message = "Price must be at least 0")
    private Double price;
    private String imageUrl;
    private String category;
    private String categoryImageUrl;

    @Min(value = 0, message = "Stock must be at least 0")
    private Integer stock;
}
