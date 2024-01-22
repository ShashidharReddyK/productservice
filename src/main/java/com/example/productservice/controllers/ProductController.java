package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotExistException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier(value = "selfProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/get-{id}")
    public ResponseEntity<Product> getProductByIdResponse(@PathVariable("id") Long id) throws ProductNotExistException {
        return new ResponseEntity<>(
                productService.getProduct(id),
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PatchMapping("/patch-{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.patchProduct(id, product);
    }

    @PutMapping("/put-{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.putProduct(id, product);
    }

    @DeleteMapping("/delete-{id}")
    public Product deleteProduct(@PathVariable("id") Long id) throws ProductNotExistException {
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

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<Void> handleProductNotExistException() {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
