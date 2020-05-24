package com.molinadario.project;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Log LOG = LogFactory.getLog(WebSecurityConfig.class);

	private String[] resources = new String[] { "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**",
			"/layer/**" };

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	@Qualifier("userDetailsImplements")
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		LOG.info("METHOD configure() PARAM: " + http.toString());

		http.authorizeRequests().antMatchers(resources).permitAll().antMatchers("/", "/login", "/signup").permitAll()
		.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/welcome").failureUrl("/login?error=true").usernameParameter("username")
		.passwordParameter("password").and().csrf().disable().logout().permitAll()
		.logoutSuccessUrl("/login?logout");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		LOG.info("METHOD passwordEncoder()");

		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		LOG.info("METHOD configureGlobal()" + auth.toString());

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
