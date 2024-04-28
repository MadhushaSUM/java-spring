package com.sum.springPostgreWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    ProductDB db;

    public List<Product> getAllProducts() {
        return db.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return db.findById(id);
    }

    public void addProduct(Product product) {
        db.save(product);
    }
}
