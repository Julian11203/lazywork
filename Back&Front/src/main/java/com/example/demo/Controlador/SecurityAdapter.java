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
                .logout()
                .logoutUrl("/logout") // Especifica la URL de logout
                .logoutSuccessUrl("http://localhost:8080/") // Establece la URL a la que se redireccionará después del logout
                .permitAll() // Permite a cualquier usuario acceder a la URL de logout

                .and()

                .authorizeHttpRequests(a -> a
                        .antMatchers("/**", "/**/funciones.js", "/**/code.jquery.com_jquery-3.7.0.min.js").permitAll()
                        .anyRequest().authenticated()
                ).exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                ).oauth2Login();

        http.cors().and().csrf().disable();
    }

}
