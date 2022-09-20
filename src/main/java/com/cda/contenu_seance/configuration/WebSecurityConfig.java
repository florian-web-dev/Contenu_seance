package com.cda.contenu_seance.configuration;

import com.cda.contenu_seance.service.IntervenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//implements AuthenticationSuccessHandler

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    IntervenantService intervenantService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //Permet de restreindre l'accès en fonction de l' HttpServletRequestutilisation
                .authorizeRequests()
                //Permet d'autoriser l'accès a toutes les rôles au URI de antMatchers
                .antMatchers("/login","/css/**").permitAll()
                //Permet d'autoriser l'accès a un rôle et a toutes les URI qui commencent par les arguments de antMatchers
                .antMatchers("/Coordinateur/**","/admin").hasAuthority("coordinateur")
                //Permet d'autoriser l'accès a plusieurs rôle et a toutes les URI qui commencent par les arguments de antMatchers
                .antMatchers("/Formateur/**").hasAnyAuthority("formateur","coordinateur")
                //Toutes les autres requêtes nécessitent une authentification
                .anyRequest().authenticated()
                //je demande a spring security d'ignorer l'absence de token dans le formulaire de h2-console
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                //je configure l'URI de la page login.
                .formLogin().loginPage("/login")
                //je configure la redirection après authentification.
                .successHandler(myAuthenticationSuccessHandler())
//                .defaultSuccessUrl("/bob")

//                .loginProcessingUrl()
                .permitAll()
                .and()
                //je configure l'URI logout.
                .logout()
                .logoutUrl("/logout")
                .permitAll();

        http.headers().frameOptions().disable();
//        https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/headers.html#headers-xss-protection
    }

    /**
     *
     * @return une intance de la class MySimpleUrlAuthenticationSuccessHandler
     */
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){


        return new MySimpleUrlAuthenticationSuccessHandler();
    }

}
