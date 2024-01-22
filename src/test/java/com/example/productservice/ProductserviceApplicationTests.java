package com.example.productservice;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.services.SelfProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductserviceApplicationTests {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SelfProductService selfProductService;

//    @Test
//    void contextLoads() {
//    }
//
//
//
//
    @Test
    void testQueries(){
        Product product = new Product();
        product.setTitle("Iphone 15 pro");
        Category category = new Category();
        category.setName("Iphone");
////

        product.setDescription("Latest Iphone  15 pro");
        product.setPrice(149000.00);
        product.setCategory(category);

//    productRepository.save(product);
//         productRepository.findById(1L);
        selfProductService.patchProduct(11L,product);
    }
}
