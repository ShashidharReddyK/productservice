package com.example.productservice.repositories;

import com.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);

    List<Category> findAllBy();

    Category save(Category category);
}
