package com.acme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.openid.OpenIDAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    OAuthProviderProcessingFilter appDirectProcessingFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/*.html", "/**/*.js", "/").permitAll()
                .antMatchers("/api/companies", "/api/companies/**").permitAll() // unsecured by design
                .antMatchers("/api/users", "/api/users/**").permitAll() // unsecured by design
                .antMatchers("/api/app-direct/*").permitAll() // security using the app direct filter
                .anyRequest().authenticated()

                .and()
                .openidLogin()
                .permitAll()
                .authenticationUserDetailsService(new CustomUserDetailsService())

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("https://www.appdirect.com");

        http
                .addFilterAfter(appDirectProcessingFilter, OpenIDAuthenticationFilter.class);
    }
}
