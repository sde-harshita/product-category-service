package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dto.CategoryDto;
import org.example.productcatalogservice.dto.ProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return null;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        try {
            if (productId < 1) {
                throw new IllegalArgumentException("Product ID should be a positive integer");
            }
            Product product = iProductService.getProductById(productId);
            ProductDto productDto = mapToProductDto(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.addIfAbsent("Author", "Harshita");
            return new ResponseEntity<>(productDto, headers, HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/product/make")
    public Product createProduct(@RequestBody Product product) {
        return product;
    }

    private ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
