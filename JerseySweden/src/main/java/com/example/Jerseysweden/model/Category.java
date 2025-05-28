package com.example.Jerseysweden.model;

import lombok.*;

@Getter
@Setter
public class Category {
    private String name;
    private String imageUrl;

    public Category(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
