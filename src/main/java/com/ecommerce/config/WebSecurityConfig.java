package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/home").permitAll()
                //Author
                .antMatchers("/product/").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/product/new").hasAnyAuthority("ADMIN")
                .antMatchers("/product/update/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/product/delete/{id}").hasAuthority("ADMIN")
                .antMatchers("/account/").hasAnyAuthority("ADMIN", "MODERATOR")
                .antMatchers("/account/update/{id}").hasAuthority("ADMIN")
                .antMatchers("/account/delete/{id}").hasAuthority("ADMIN")
                //Own Account Updates
                .antMatchers("/account/update").hasAnyAuthority("ADMIN", "MODERATOR", "USER")
                //Book
                .antMatchers("/book/").hasAnyAuthority("ADMIN", "MODERATOR", "USER")
                .antMatchers("/book/new").hasAnyAuthority("ADMIN", "MODERATOR")
                .antMatchers("/book/update/{id}").hasAnyAuthority("ADMIN", "MODERATOR")
                .antMatchers("/book/delete/{id}").hasAuthority("ADMIN")
                //Publisher
                .antMatchers("/publisher/").hasAnyAuthority("ADMIN", "MODERATOR", "USER")
                .antMatchers("/publisher/new").hasAnyAuthority("ADMIN", "MODERATOR")
                .antMatchers("/publisher/update/{id}").hasAnyAuthority("ADMIN", "MODERATOR")
                .antMatchers("/publisher/delete/{id}").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable().formLogin().permitAll()
                .loginPage("/login")
                .failureUrl("/login?wrong=true")
                .defaultSuccessUrl("/panel")
                .usernameParameter("userName")
                .passwordParameter("password")
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
        http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
        http.headers().frameOptions().disable();
    }
}
