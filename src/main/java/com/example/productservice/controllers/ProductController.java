package com.example.productservice.controllers;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/get-{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProduct(id);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PatchMapping("/patch-{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        Product p = new Product();
        p.setId(id);
        p.setTitle(product.getTitle());
        return p;
    }

    @PutMapping("/put-{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.putProduct(id, product);
    }

    @DeleteMapping("/delete-{id}")
    public Product deleteProduct(@PathVariable("id") Long id){
        // delete product with id
        return productService.deleteProduct(id);
    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return productService.getCategories();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){
        return productService.getProductsByCategory(category);
    }
}
