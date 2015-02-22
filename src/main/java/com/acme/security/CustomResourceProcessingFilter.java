package com.acme.security;

import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * TODO refactor
 */
public class CustomResourceProcessingFilter extends ProtectedResourceProcessingFilter {
    private List<RequestMatcher> requestMatchers;

    public CustomResourceProcessingFilter(List<RequestMatcher> requestMatchers) {
        this.requestMatchers = requestMatchers;
    }

    @Override
    protected boolean requiresAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) {

        boolean matches = false;

        if (requestMatchers != null && !requestMatchers.isEmpty()) {
            for (RequestMatcher requestMatcher : requestMatchers) {
                if (requestMatcher.matches(request)) {
                    matches = true;
                    break;
                }
            }
        }

        return matches;
    }
}
