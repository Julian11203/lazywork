package com.example.demo.Controlador;


import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(a -> {
                    try {
                        a
                                .antMatchers("/**", "/**/funciones.js", "/**/code.jquery.com_jquery-3.7.0.min.js").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .logout()
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("http://localhost:8080/")
                                .permitAll();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .oauth2Login();

        http.cors().and().csrf().disable();
    }

}