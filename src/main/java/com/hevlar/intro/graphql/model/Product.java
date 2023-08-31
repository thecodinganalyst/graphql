package com.hevlar.intro.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    String id;
    String name;
    String description;
    BigDecimal price;
    String category;

    public Product(String name, String description, BigDecimal price, String category){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
