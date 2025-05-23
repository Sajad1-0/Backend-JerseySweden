package com.example.Jerseysweden.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerInfo {

    private String name;
    private String address;
    private String email;

    public CustomerInfo(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
