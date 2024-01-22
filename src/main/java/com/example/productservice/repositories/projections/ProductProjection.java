package com.example.productservice.repositories.projections;

public interface ProductProjection {
    Long getId();
    String getTitle();
    double getPrice();
    String getDescription();
    String getImage();
    Long getCategoryId();
    String getCategoryName();
}
