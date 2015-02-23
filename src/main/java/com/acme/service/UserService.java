package com.acme.service;

import com.acme.model.User;
import com.acme.repository.UserRepository;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String getEditionCodeForUser(String openId) {
        // if we found none, we have a major problem, i'll trust the system and get the first one
        User user = Iterables.getFirst(userRepository.findByOpenId(openId), null);

        return user != null ? user.getCompany().getEditionCode() : null;
    }
}
