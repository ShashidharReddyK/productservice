package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProduct){
        Product product = new Product();
        product.setId(fakeStoreProduct.getId());
        product.setTitle(fakeStoreProduct.getTitle());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImage(fakeStoreProduct.getImage());
        return product;
    }
    @Override
    public Product getProduct(Long id) {
        FakeStoreProductDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(productDto);
    }

    private List<Product> convertFakeStoreProductDtoListToProductList(FakeStoreProductDto[] fakeStoreProductDtos){
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    private List<Product> convertFakeStoreProductDtoListToProductList(List<FakeStoreProductDto> fakeStoreProductDtos){
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    public List<Product> getAllProducts() {

        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class);
        FakeStoreProductDto[] allFakeStoreProducts = response.getBody();
        return convertFakeStoreProductDtoListToProductList(allFakeStoreProducts);

//        List<FakeStoreProductDto> allFakeStoreProducts = restTemplate.getForObject("https://fakestoreapi.com/products/",
//                ArrayList.class);
//        return convertFakeStoreProductDtoListToProductList(allFakeStoreProducts);
    }
}
