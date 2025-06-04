package com.example.Jerseysweden.model;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerInfo {
    @NotNull(message = "Name is required")
    @Size(min = 4, max = 15, message = "Name must be between 4 to 15 characters")
    private String name;

    @NotBlank(message = "Address is required and can't be empty")
    private String address;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email is required")
    private String email;

    public CustomerInfo() {
        // Default constructor för Jackson
        // För deserialisering
    }

    public CustomerInfo(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

}
