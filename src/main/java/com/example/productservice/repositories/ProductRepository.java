package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleContaining(String word);

    @NonNull
    Optional<Product> findById(Long id);


//    @Query(value = "select p.* from product p", nativeQuery = true)
    List<Product> findAllBy();

    Long removeById(Long id);


    Product save(Product product);

}
