package com.acme.service;

import com.acme.model.Company;
import com.acme.model.User;
import com.acme.repository.CompanyRepository;
import com.acme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Maintains the link between our users,companies and AppDirect's
 */
@Service
@Transactional
public class SynchronizationService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    public Company createCompany(com.acme.serializer.appdirect.Company adCompany, String editionCode) {
        Company company = new Company(
                adCompany.getName(),
                adCompany.getUuid(),
                editionCode
        );
        
        companyRepository.save(company);
        
        return company;
    }

    public User createUser(com.acme.serializer.appdirect.User adUser, String companyIdentifier) {
        User user = new User(
                adUser.getEmail(),
                adUser.getFirstName(),
                adUser.getLastName(),
                adUser.getOpenId(),
                adUser.getUuid()
        );
        user.setCompany(companyRepository.findByUuid(companyIdentifier).get(0));
        
        userRepository.save(user);
        
        return user;
    }

    public void updateSubscription(String companyIdentifier, String editionCode) {
        Company company = companyRepository.findByUuid(companyIdentifier).get(0);
        company.setEditionCode(editionCode);
        
        companyRepository.save(company);
    }
}
