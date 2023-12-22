package com.example.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String name;
    private double price;
    private int categoryId;
    private String description;
    private String imageUrl;

}
