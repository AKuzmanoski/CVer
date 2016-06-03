package com.cver.team.config;

import com.cver.team.auth.LocalLoginFailureHandler;
import com.cver.team.auth.LocalLoginSuccessHandler;
import com.cver.team.filters.CsrfHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


/**
 * Created by Dimitar on 4/2/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.logout()
              .logoutUrl("/logout");

        httpSecurity.formLogin()
              .usernameParameter("username")
              .passwordParameter("password")
              .loginProcessingUrl("/doLogin")

              .successHandler(new LocalLoginSuccessHandler())
              .failureHandler(new LocalLoginFailureHandler());


        httpSecurity.authorizeRequests()
                .antMatchers("/doLogin").permitAll()
                .antMatchers("logout").permitAll()
                .antMatchers("/testGet").permitAll();


        httpSecurity.csrf()
              .csrfTokenRepository(csrfTokenRepository());

        httpSecurity.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);


    }

    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
