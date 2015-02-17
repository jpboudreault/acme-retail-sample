package com.acme.controller;

import com.acme.exception.ResourceNotFoundException;
import com.acme.model.MonthlySale;
import com.acme.model.Product;
import com.acme.repository.MonthlySaleRepository;
import com.acme.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class SalesController {

    @Autowired
    MonthlySaleRepository monthlySaleRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("products/{productId}/monthly-sales")
    public Iterable<MonthlySale> products(@PathVariable long productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new ResourceNotFoundException();
        }
        
        return monthlySaleRepository.findByProduct(product);
    }
}
