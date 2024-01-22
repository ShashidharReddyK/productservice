package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public SelfCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAllBy();
        return categories;
    }

    public List<Product> getProductsByCategory(String category) {
        Optional<Category> categoryOptional = categoryRepository.findByName(category);

        if(categoryOptional.isEmpty()){
            return null;
        }

        List<Product> productsByCategory = categoryRepository.findAllByCategoryId(categoryOptional.get().getId());
        return productsByCategory;
    }
}
