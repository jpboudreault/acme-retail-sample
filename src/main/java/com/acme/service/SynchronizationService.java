package com.acme.service;

import com.acme.model.Company;
import com.acme.model.User;
import com.acme.repository.CompanyRepository;
import com.acme.repository.UserRepository;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
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

    public void removeUser(String openId, Long companyId) {
        Iterable<User> users = userRepository.findByOpenId(openId);
        if (Iterables.isEmpty(users)) {
            // unknown user? silent failure
            LOG.warn(String.format("No user to remove, openId not found : %s", openId));
            return;
        }

        User user = Iterables.getFirst(users, null);

        // sanity check on the user's company
        Preconditions.checkState(
                companyId.equals(user.getCompany().getId()), 
                String.format("User %s doesn't belong in the correct company %d", openId, companyId));
        
        userRepository.delete(user); // we checked that there's one already
    } 
        
    public void updateSubscription(Long companyId, String editionCode) {
        Company company = companyRepository.findOne(companyId);
        company.setEditionCode(editionCode);
        
        companyRepository.save(company);
    }

    public void applyNotice(Long companyId, String notice) {
        Company company = companyRepository.findOne(companyId);
        company.setNotice(notice);
        
        companyRepository.save(company);
    }

    public void cancelSubscription(Long companyId) {
        // in the real world, we would probably have an active state on the company and wouldn't be destroying it

        Company company = companyRepository.findOne(companyId);
        if (company == null) {
            // unknown company? silent failure
            LOG.warn(String.format("No subscription to cancel, company not found : %d", companyId));
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
