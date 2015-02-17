package com.acme.repository;

import java.util.List;

import com.acme.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
}


