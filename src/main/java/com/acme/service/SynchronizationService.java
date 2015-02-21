package com.acme.service;

import com.acme.model.Company;
import com.acme.model.User;
import com.acme.repository.CompanyRepository;
import com.acme.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Maintains the link between our users,companies and AppDirect's
 */
@Service
@Transactional
public class SynchronizationService {
    private final Log LOG = LogFactory.getLog(SynchronizationService.class);

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    public Company createCompany(com.acme.serializer.xml.Company adCompany, String editionCode) {
        Company company = new Company(
                adCompany.getName(),
                adCompany.getUuid(),
                editionCode
        );
        
        companyRepository.save(company);
        
        return company;
    }

    public User createUser(com.acme.serializer.xml.User adUser, Long companyId) {
        User user = new User(
                adUser.getEmail(),
                adUser.getFirstName(),
                adUser.getLastName(),
                adUser.getOpenId(),
                adUser.getUuid()
        );
        user.setCompany(companyRepository.findOne(companyId));
        
        userRepository.save(user);
        
        return user;
    }

    public void updateSubscription(Long companyId, String editionCode) {
        Company company = companyRepository.findOne(companyId);
        company.setEditionCode(editionCode);
        
        companyRepository.save(company);
    }

    public void cancelSubscription(Long companyId) {
        // in the real world, we would probably have an active state on the company and wouldn't be destroying it

        Company company = companyRepository.findOne(companyId);
        if (company == null) {
            // unknown company? silent failure
            LOG.warn("No subscription to cancel, company not found :" + companyId);
            return;
        }
        
        // delete users and company
        Iterable<User> users = userRepository.findByCompany(company);
        if (users != null) {
            userRepository.delete(users);
        }

        companyRepository.delete(company);
    }
}
