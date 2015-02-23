package com.acme.security;

import com.acme.service.UserService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;

public class CustomUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
    @Autowired
    private UserService userService;

    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        String edition = userService.getEditionCodeForUser(token.getIdentityUrl());

        // if it's empty, we have a major problem
        Preconditions.checkNotNull(edition, String.format("Couldn't find the edition of user %s", token.getIdentityUrl()));

        // this will create a role from the edition code, ex: ROLE_PREMIUM, ROLE_FREE
        String role = String.format("ROLE_%s", edition.toUpperCase());

        return new User(token.getName(), "", AuthorityUtils.createAuthorityList(role));
    }
}