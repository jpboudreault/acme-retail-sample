package com.acme.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/*.html", "/**/*.js", "/").permitAll()
                .antMatchers("/api/companies", "/api/companies/**").permitAll()
                .antMatchers("/api/users", "/api/users/**").permitAll()
                .antMatchers("/api/app-direct/*").permitAll() // todo fix this someday
                .anyRequest().authenticated()
                .and()
                .openidLogin()
                .loginProcessingUrl("login-openid")
                .authenticationUserDetailsService(new CustomUserDetailsService())
//                .authenticationUserDetailsService()
                .defaultSuccessUrl("/")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new CustomUserDetailsService())
                .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER");
    }
}
