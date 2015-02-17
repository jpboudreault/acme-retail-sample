package com.acme.repository;

import com.acme.model.MonthlySale;
import com.acme.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MonthlySaleRepository extends CrudRepository<MonthlySale, Long> {
    List<MonthlySale> findByProduct(Product product);
}


