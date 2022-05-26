package com.example.hostpitalapp.Security;

import com.example.hostpitalapp.Security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        String encodedPWD = passwordEncoder.encode("1234");
        System.out.println(encodedPWD);
        auth.inMemoryAuthentication()
                .withUser("user1").password(encodedPWD).roles("USER")
                .and()
                .withUser("admin").password(encodedPWD).roles("ADMIN", "USER");
         */
        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role from users_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);
         */

        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/", "/index/**", "/webjars/**", "/resources/**", "/static/**", "/images/**" ).permitAll();
        //http.authorizeRequests().antMatchers("/admin/**" ).hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/admin/**" ).hasAuthority("ADMIN");
        //http.authorizeRequests().antMatchers("/user/**" ).hasRole("USER");
        http.authorizeRequests().antMatchers("/user/**" ).hasAuthority("USER");
        http.authorizeRequests().anyRequest().authenticated(); // All HTTP REQUESTS NEED AUTHENTIFICATION
        http.exceptionHandling().accessDeniedPage("/403");
    }
}