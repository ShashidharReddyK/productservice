package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public Product getProduct(Long id);
    public List<Product> getAllProducts();
    public Product deleteProduct(Long id);
    public Product patchProduct(Long id, Product product);
    public Product putProduct(Long id, Product product);
    public Product addProduct(Product product);
    List<Category> getCategories();
    List<Product> getProductsByCategory(String category);
}
