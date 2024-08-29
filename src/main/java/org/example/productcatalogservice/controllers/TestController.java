package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String getMessage() {
        return "Welcome to Product Catalog Service";
    }
}
