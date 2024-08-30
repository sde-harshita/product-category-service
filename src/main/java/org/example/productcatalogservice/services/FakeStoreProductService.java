package org.example.productcatalogservice.services;

import org.example.productcatalogservice.dto.FakeStoreProductDto;
import org.example.productcatalogservice.dto.ProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProduct = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id).getBody();
        return mapFSToProduct(fakeStoreProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProduct = mapProductToFSDto(product);
        FakeStoreProductDto newfakeStoreProduct = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProduct, FakeStoreProductDto.class).getBody();
        return mapFSToProduct(newfakeStoreProduct);
    }

    private Product mapFSToProduct(FakeStoreProductDto fakeStoreProduct) {
        Product product = new Product();
        product.setId(fakeStoreProduct.getId());
        product.setName(fakeStoreProduct.getTitle());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImageUrl(fakeStoreProduct.getImage());
        product.setPrice(fakeStoreProduct.getPrice());
        if(fakeStoreProduct.getCategory() != null) {
            Category category = new Category();
            category.setName(fakeStoreProduct.getCategory());
            product.setCategory(category);
        }
        return product;
    }

    private FakeStoreProductDto mapProductToFSDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
//        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }
}
