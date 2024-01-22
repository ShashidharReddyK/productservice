package com.example.productservice.repositories;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);

    List<Category> findAllBy();

    @Query("SELECT p FROM Product p WHERE p.category.id = :id")
    List<Product> findAllByCategoryId(Long id);

    Category save(Category category);
}
