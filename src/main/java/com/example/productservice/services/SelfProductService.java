package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotExistException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service("selfProductService")
public class SelfProductService implements ProductService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(Long id) throws ProductNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()){
            throw new ProductNotExistException("Product with id " + id + " does not exist");
        }
//        Product product = productOptional.get();
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllBy();
//        return null;
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotExistException("Product with id " + id + " does not exist");
        }
        Long idDeleted = productRepository.removeById(id);


        return productOptional.get();
    }

    @Override
    public Product patchProduct(Long id, Product product){
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        Optional<Product> productOptional = productRepository.findById(id);

        Product finalProduct = productOptional.get();

        if(categoryOptional.isPresent()){
            finalProduct.setCategory(categoryOptional.get());
        }

        if(product.getTitle() != null && !product.getTitle().isEmpty()){
            System.out.println("I am here");
            finalProduct.setTitle(product.getTitle());
        }

        if(product.getDescription() != null && !product.getDescription().isEmpty()){
            finalProduct.setDescription(product.getDescription());
        }

        if(product.getImage() != null && !product.getImage().isEmpty()){
            finalProduct.setImage(product.getImage());
        }

        if(product.getPrice() != null && product.getPrice() > 0){
            finalProduct.setPrice(product.getPrice());
        }
//        Product product1 =

//        if(productOptional.isEmpty()){
//            throw new ProductNotExistException("Product with id " + id + " does not exist");
//        }

        return productRepository.save(finalProduct);
    }

    @Override
    public Product putProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product addProduct(Product product){
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        if(categoryOptional.isEmpty()) {
//            Category category = categoryRepository.save(product.getCategory());
//            product.setCategory(category);
        }else{
            product.setCategory(categoryOptional.get());
        }

        return productRepository.save(product);
    }

    @Override
    public List<Category> getCategories() {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return null;
    }

}
