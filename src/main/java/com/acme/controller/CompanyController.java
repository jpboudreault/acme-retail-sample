package com.acme.controller;

import com.acme.model.Company;
import com.acme.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping("companies")
    public Iterable<Company> companies() {
        return companyRepository.findAll();
    }
}
