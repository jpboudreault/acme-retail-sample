package com.acme.controller;

import com.acme.model.Product;
import com.acme.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("products")
    public Iterable<Product> products() {
        return productRepository.findAll();
    }
}
