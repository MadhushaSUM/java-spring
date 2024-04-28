package com.sum.springPostgreSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    ProductDB db;

    public List<Product> getAllProducts() {
        return db.findAll();
    }

    public void addProduct(Product product) {
        db.save(product);
    }
}
