package com.acme.repository;

import com.acme.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<User, Long> {
}


