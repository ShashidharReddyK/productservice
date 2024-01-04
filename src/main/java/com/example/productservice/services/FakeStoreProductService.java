package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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
        if(fakeStoreProduct == null){
            return null;
        }

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

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product){
        if(product == null){
            return null;
        }

        FakeStoreProductDto fakeStoreProduct = new FakeStoreProductDto();
        fakeStoreProduct.setTitle(product.getTitle());
        fakeStoreProduct.setPrice(product.getPrice());
        fakeStoreProduct.setCategory(product.getCategory().getName());
        fakeStoreProduct.setDescription(product.getDescription());
        fakeStoreProduct.setImage(product.getImage());
        return fakeStoreProduct;
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
    }

    public Product deleteProduct(Long id) {
        // delete product with id
        RequestCallback requestCallback = restTemplate.httpEntityCallback(id, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.DELETE, requestCallback, responseExtractor);
//
//        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
//                FakeStoreProductDto.class);
//        restTemplate.delete("https://fakestoreapi.com/products/"+id);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    public Product patchProduct(Long id, Product product) {
        // update product with id
        return null;
    }

    public Product putProduct(Long id, Product product) {
        // update product with id
        FakeStoreProductDto productDto = convertProductToFakeStoreProductDto(product);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    public Product addProduct(Product product) {
        // add product
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreProductDto(product);

        FakeStoreProductDto productDto = restTemplate.postForObject("https://fakestoreapi.com/products/",
                fakeStoreProductDto, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(productDto);
    }

    public List<Category> getCategories(){
        ResponseEntity<String[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products/categories",
                String[].class);
        String[] categories = response.getBody();
        List<Category> categoryList = new ArrayList<>();
        for(String category : categories){
            Category c = new Category();
            c.setName(category);
            categoryList.add(c);
        }
        return categoryList;
    }

    public List<Product> getProductsByCategory(String category){
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products/category/"+category,
                FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        return convertFakeStoreProductDtoListToProductList(fakeStoreProductDtos);
    }
}
