package com.example.productservice.controllers;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.CategoryService;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("selfCategoryService") CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/all-categories")
    public List<Category> getAllCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/products-by-category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){
        return categoryService.getProductsByCategory(category);
    }

}
