package org.example.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice.models.Product;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private List<Product> products;
}
