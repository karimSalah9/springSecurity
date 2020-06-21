package com.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// add our users to memory
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication().withUser(users.username("key").password("123456").roles("EMPLOYEE"))
				.withUser(users.username("karim").password("123456").roles("ADMIN"))
				.withUser(users.username("kotb").password("123456").roles("MANAGER"));
	}

	// this is for use our custom login form
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/showMyloginPage")
			.loginProcessingUrl("/AthunticatedUser")
			.permitAll();

	}

}
