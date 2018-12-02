package com.klezovich.robot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("Klezovich").password("password").roles("ADMIN");
    }
	
   @Override 
   public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/robots");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();
    	
    	  http
          .authorizeRequests()
              .antMatchers("/**").permitAll() 
              .anyRequest().authenticated()
              .and()
          .formLogin()
              .loginPage("/login")
              .loginProcessingUrl("/perform_login")
              .defaultSuccessUrl("/robot_control_page", true)
              .permitAll()
              .and()
          .logout()                                    
              .permitAll();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // ...
     
     
}