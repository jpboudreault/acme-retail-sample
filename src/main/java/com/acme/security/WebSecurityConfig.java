package com.acme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.openid.OpenIDAuthenticationFilter;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${logout.url}")
    private String logoutUrl;

    @Autowired
    OAuthProviderProcessingFilter appDirectProcessingFilter;

    @Autowired
    AuthenticationUserDetailsService<OpenIDAuthenticationToken> customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/*.html", "/**/*.js", "/").permitAll()

                // unsecured by design
                .antMatchers("/api/companies", "/api/companies/**").permitAll()
                .antMatchers("/api/users", "/api/users/**").permitAll() // unsecured by design

                // secured using the appDirectProcessingFilter
                .antMatchers("/api/app-direct/*").permitAll()

                .anyRequest().authenticated()

                .and()
                .openidLogin()
                .permitAll()
                .authenticationUserDetailsService(customUserDetailsService)

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl(logoutUrl);

        http
                .addFilterAfter(appDirectProcessingFilter, OpenIDAuthenticationFilter.class);
    }
}
