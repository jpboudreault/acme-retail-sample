package com.acme.service;

import com.acme.repository.CompanyRepository;
import com.acme.repository.UserRepository;
import com.acme.serializer.appdirect.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Maintains the link between our users and companies and AppDirect's 
 */
@Service
public class SubscriptionService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;
    
    public Result subscriptionOrder(String url) {
        // FIXME implement
        return null;
    }
}
