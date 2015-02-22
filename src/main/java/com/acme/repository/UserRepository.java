package com.acme.repository;

import com.acme.model.Company;
import com.acme.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public Iterable<User> findByCompany(Company company);
    public Iterable<User> findByOpenId(String openId);
}


