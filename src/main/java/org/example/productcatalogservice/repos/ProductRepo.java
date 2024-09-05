package org.example.productcatalogservice.repos;

import org.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
