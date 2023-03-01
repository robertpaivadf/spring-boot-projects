package io.github.robertpaivadf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity //Desligando totalmente todas as configurações defaults do Spring Security
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//    //criando um bean para criptografar e descriptografar a senha do usuário
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    //Aqui será autenticação (login, senha)
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("fulano")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER");
//    }



    //Aqui será autorização (permissão de acesso aos endpoints)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable(); //desabilitando permite executar os metodos POST e DELETE

    }

    //Aqui será autenticação (login, senha)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("robert")
                .password(passwordEncoder().encode("1565"))
                .roles("ADMIN");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
