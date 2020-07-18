package com.biblio.client.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableOAuth2Sso
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyLogoutHandler myLogoutHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/login**", "/logout**", "/", "/static/css/**", "/static/js/**", "/registration**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .addLogoutHandler(myLogoutHandler);
    }
}
