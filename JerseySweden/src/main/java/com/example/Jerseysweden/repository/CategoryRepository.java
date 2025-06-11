package com.example.Jerseysweden.repository;


import com.example.Jerseysweden.model.Category;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class CategoryRepository {
    private final Map<String, Category> categories = new HashMap<>();

    public CategoryRepository() {
        categories.put("La Liga", new Category("La Liga", "https://res.cloudinary.com/dv9izlvzw/image/upload/v1715862544/Jersey-sweden/Logos/Spanish-La-Liga-Logo_knfb1q.png"));
        categories.put("Primier League", new Category("Primier League", "https://res.cloudinary.com/dv9izlvzw/image/upload/v1715862543/Jersey-sweden/Logos/Premier-League-Logo_rty5q3.png"));
        categories.put("Serie A", new Category("Serie A", "https://res.cloudinary.com/dv9izlvzw/image/upload/v1715862541/Jersey-sweden/Logos/Italian-Serie-A-Logo_bc7xqt.png"));
        categories.put("Other Leagues", new Category("Other Leagues", "https://res.cloudinary.com/dv9izlvzw/image/upload/v1716726745/Jersey-sweden/Logos/obikfulmck6ekjkrfjvk.png"));
        categories.put("International", new Category("International", "https://res.cloudinary.com/dv9izlvzw/image/upload/v1715862540/Jersey-sweden/Logos/FIFA-Logo-1977-1998_crytcp.png"));
    }

    public Category getLeagueByCategoryName(String name) {
        return categories.get(name);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
}
