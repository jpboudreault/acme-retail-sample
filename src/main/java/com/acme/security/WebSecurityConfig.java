package com.acme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.openid.OpenIDAuthenticationFilter;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    OAuthProviderProcessingFilter oAuthProviderProcessingFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/*.html", "/**/*.js", "/").permitAll()
                .antMatchers("/api/companies", "/api/companies/**").permitAll()
                .antMatchers("/api/users", "/api/users/**").permitAll()
                .antMatchers("/api/app-direct/*").permitAll() // todo fix this someday
                .anyRequest().authenticated();
        
        http
                .openidLogin()
                .permitAll()
                .authenticationUserDetailsService(new CustomUserDetailsService())
                .attributeExchange("https://www.appdirect.com.*")
                .attribute("email")
                .type("http://axschema.org/contact/email")
                .required(true)
                .and()
                .attribute("firstname")
                .type("http://axschema.org/namePerson/first")
                .required(true)
                .and()
                .attribute("lastname")
                .type("http://axschema.org/namePerson/last")
                .required(true);

        http
                .addFilterAfter(oAuthProviderProcessingFilter, OpenIDAuthenticationFilter.class);
    }
}
