package com.acme.service;

import com.acme.repository.CompanyRepository;
import com.acme.repository.UserRepository;
import com.acme.serializer.AppDirectResponse;
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
    
    public AppDirectResponse subscriptionOrder(String url) {
        // FIXME implement
        return null;
    }
}
