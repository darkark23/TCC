package br.net.iesb.config.security;

import br.net.iesb.service.security.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AutenticacaoService autenticacaoService;

    //Configuracao de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configuracao de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated().and().httpBasic();
    }

    //Configuracao de recursos estaticos
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    public static void main(String[] args){
        System.out.println(new BCryptPasswordEncoder().encode("ledor"));
    }



}
