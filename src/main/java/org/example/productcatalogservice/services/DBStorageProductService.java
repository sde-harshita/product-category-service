package org.example.productcatalogservice.services;

import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.models.Status;
import org.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Primary
public class DBStorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        Date d = new Date();
//        product.setCreatedAt(d);
//        product.setLastUpdateAt(d);
//        product.setStatus(Status.ACTIVE);
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
