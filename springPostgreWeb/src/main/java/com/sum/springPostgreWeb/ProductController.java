package com.sum.springPostgreWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("product/{productId}")
    public Optional<Product> getProduct(@PathVariable int productId) {
        return service.getProductById(productId);
    }

    @PostMapping("products")
    public void addProduct(@RequestBody Product product) {
        service.addProduct(product);
    }
}
