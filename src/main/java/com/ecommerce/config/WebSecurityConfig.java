package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/ecommerce/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/login.png").permitAll()
                .antMatchers("/register.png").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/images/{name}").permitAll()
                //Author
                .antMatchers("/cart/**").hasAnyAuthority("USER")
                .antMatchers("/cart/all").hasAnyAuthority("USER")
                .antMatchers("/cart/add/{id}").hasAnyAuthority("USER")
                .antMatchers("/product/").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/product/new").hasAnyAuthority("ADMIN")
                .antMatchers("/product/update").hasAnyAuthority("ADMIN")
                .antMatchers("/product/update/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/product/add").hasAuthority("ADMIN")
                .antMatchers("/adminPanel").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable().formLogin().permitAll()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/ecommerce/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .httpBasic()
                .and()
                .logout().clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
        http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
        http.headers().frameOptions().disable();
    }
}
