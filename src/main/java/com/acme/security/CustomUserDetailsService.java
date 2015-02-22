package com.acme.security;

import com.acme.repository.UserRepository;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;

public class CustomUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        // if we found one, we have a major problem, i'll trust the system and get the first one
        com.acme.model.User user = Iterables.get(userRepository.findByOpenId(token.getIdentityUrl()), 0);

        // this will create a role from the edition code, ex: ROLE_PREMIUM, ROLE_FREE
        String role = String.format("ROLE_%s", user.getCompany().getEditionCode().toUpperCase());
        
        return new User(token.getName(), "", AuthorityUtils.createAuthorityList(role));
    }
}