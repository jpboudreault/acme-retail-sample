package com.acme.service;

import com.acme.model.MonthlySale;
import com.acme.model.Product;
import com.acme.repository.MonthlySaleRepository;
import com.acme.repository.ProductRepository;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class BootstrapDataService implements InitializingBean {
    private static final int SALES_LIMIT = 1000;
    
    private final Log LOG = LogFactory.getLog(BootstrapDataService.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MonthlySaleRepository monthlySaleRepository;

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        LOG.info("Bootstrapping data...");

        createProducts();

        createSales();

        LOG.info("...Bootstrapping completed");
    }

    private void createProducts() {
        if (productRepository.count() > 0) {
            return;
        }

        LOG.info("... creating products");

        List<Product> products = ImmutableList.of(
                new Product("Ketchup", new BigDecimal("2.49")),
                new Product("Mustard", new BigDecimal("1.49")),
                new Product("Sausage", new BigDecimal("3.49")),
                new Product("Bread", new BigDecimal("2.99"))
        );

        productRepository.save(products);
    }

    private void createSales() {
        if (monthlySaleRepository.count() > 0) {
            return;
        }

        LOG.info("... creating sales mmm?");

        Random rnd = new Random(0); // fixed seed

        ArrayList<MonthlySale> sales = Lists.newArrayList();
        for (Product product : productRepository.findAll()) {
            Collections.addAll(
                    sales,
                    new MonthlySale(product, 2015, 1, rnd.nextInt(SALES_LIMIT)),
                    new MonthlySale(product, 2014, 12, rnd.nextInt(SALES_LIMIT)),
                    new MonthlySale(product, 2014, 11, rnd.nextInt(SALES_LIMIT)),
                    new MonthlySale(product, 2014, 10, rnd.nextInt(SALES_LIMIT)),
                    new MonthlySale(product, 2014, 9, rnd.nextInt(SALES_LIMIT))
            );
        }

        monthlySaleRepository.save(sales);
    }
}
