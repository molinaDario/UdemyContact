package com.molinadario.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.molinadario.project.implement.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private String[] resources = new String[] { "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**",
			"/layer/**" };

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(resources).permitAll()  
			.antMatchers("/","/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/menu")
            .failureUrl("/login?error=true")
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/login?logout");;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		return bCryptPasswordEncoder;

	}
	

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }

}
