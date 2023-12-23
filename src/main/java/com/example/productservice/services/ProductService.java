package com.example.productservice.services;

import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public Product getProduct(Long id);
    public List<Product> getAllProducts();
}
