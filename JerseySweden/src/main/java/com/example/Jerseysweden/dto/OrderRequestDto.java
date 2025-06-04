package com.example.Jerseysweden.dto;

import com.example.Jerseysweden.model.CustomerInfo;
import com.example.Jerseysweden.model.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    @NotNull(message = "Customer info is required")
    @Valid
    private CustomerInfo customerInfo;

    @NotNull(message = "Order must include at least one item")
    @Valid
    private List<OrderItem> items;

    public OrderRequestDto(CustomerInfo customerInfo, List<OrderItem> items) {
        this.customerInfo = customerInfo;
        this.items = items;
    }

}
