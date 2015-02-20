package com.acme.service;

import com.acme.repository.CompanyRepository;
import com.acme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Maintains the link between our users,companies and AppDirect's
 */
@Service
public class SynchronizationService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;
}
