package org.example.productcatalogservice.repos;

import org.example.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
