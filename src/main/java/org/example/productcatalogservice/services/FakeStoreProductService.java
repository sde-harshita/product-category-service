package org.example.productcatalogservice.services;

import org.example.productcatalogservice.client.FakeStoreClient;
import org.example.productcatalogservice.dto.FakeStoreProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProducts = fakeStoreClient.requestForEntity("https://fakestoreapi.com/products", HttpMethod.GET, null, FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProduct : fakeStoreProducts) {
           Product product = mapFSToProduct(fakeStoreProduct);
           products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProduct = fakeStoreClient.requestForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.GET, null, FakeStoreProductDto.class, id).getBody();
        return mapFSToProduct(fakeStoreProduct);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProduct = mapProductToFSDto(product);
        FakeStoreProductDto newfakeStoreProduct = fakeStoreClient.requestForEntity("https://fakestoreapi.com/products", HttpMethod.POST, fakeStoreProduct, FakeStoreProductDto.class).getBody();
        return mapFSToProduct(newfakeStoreProduct);
    }

    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProduct = mapProductToFSDto(product);
        FakeStoreProductDto newFakeStoreProduct = fakeStoreClient.requestForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.PATCH, fakeStoreProduct, FakeStoreProductDto.class, id).getBody();
        return mapFSToProduct(newFakeStoreProduct);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProduct = mapProductToFSDto(product);
        FakeStoreProductDto newFakeStoreProduct = fakeStoreClient.requestForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.PUT, fakeStoreProduct, FakeStoreProductDto.class, id).getBody();
        return mapFSToProduct(newFakeStoreProduct);
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
