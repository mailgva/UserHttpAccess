package com.gorbatenko.userauthorities.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
                .password("12345")
                .authorities("READ")
                .build();
        var user2 = User.withUsername("jane")
                .password("12345")
                .authorities("WRITE")
                .build();
        var user3 = User.withUsername("jack")
                .password("12345")
                .authorities("ROLE_ADMIN")
                .build();
        var user4 = User.withUsername("jim")
                .password("12345")
                .roles("ADMIN")
                .build();
        manager.createUser(user1);
        manager.createUser(user2);
        manager.createUser(user3);
        manager.createUser(user4);
        return manager;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        /*http.authorizeRequests()
                .anyRequest()
                .permitAll();*/

        /*http.authorizeRequests()
                .anyRequest()
                .denyAll();*/

        /*http.authorizeRequests()
                .anyRequest()
                .hasAnyAuthority("WRITE", "READ");*/

        /*http.authorizeRequests()
                .anyRequest()
                .access("hasAuthority('WRITE')");
        */

        /*http.authorizeRequests()
                .anyRequest()
                .access("hasAuthority('READ') and !hasAuthority('DELETE')");*/

        http.authorizeRequests()
                .anyRequest().hasRole("ADMIN");


        /*http.authorizeRequests()
                .anyRequest()
                .access("T(java.time.LocalTime).now().isBefore(T(java.time.LocalTime).of(12, 0))");*/

        /*http.authorizeRequests()
                .anyRequest()
                .access("T(java.time.LocalTime).now().isAfter(T(java.time.LocalTime).of(12, 0))");*/

    }
}
