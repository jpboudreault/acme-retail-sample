package com.acme.controller;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class PermissionController {
    @RequestMapping("permissions")
    public Iterable<String> users() {
        List<String> permissions = Lists.newArrayList();

        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            permissions.add(ga.getAuthority());
        }
        
        return permissions;
    }
}
