package com.hkarabakla.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        .requestMatchers()
        .requestMatchers(
                new AntPathRequestMatcher("/oauth/token", HttpMethod.GET.toString()),
                new AntPathRequestMatcher("/oauth/token", HttpMethod.PUT.toString()),
                new AntPathRequestMatcher("/oauth/token", HttpMethod.PATCH.toString()),
                new AntPathRequestMatcher("/oauth/token", HttpMethod.DELETE.toString()))
        .and()
        .csrf()
        	.disable()
        .antMatcher("/**/user/**")
        .authorizeRequests()
        	.anyRequest().hasRole("ADMIN")
        .and()
        .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("guest").password("Z3Vlc3Q=").roles("ADMIN");
    }
}