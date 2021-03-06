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
		auth.inMemoryAuthentication()
				.withUser(users.username("key").password("123456").roles("EMPLOYEE", "ADMIN", "MANAGER"))
				.withUser(users.username("karim").password("123456").roles("EMPLOYEE","ADMIN"))
				.withUser(users.username("kotb").password("123456").roles("EMPLOYEE","MANAGER"))
						.withUser(users.username("koko").password("123456").roles("EMPLOYEE"));
	}

	// this is for use our custom login form
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				// .anyRequest().authenticated()
				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/leaders/**").hasRole("MANAGER")
				.antMatchers("/admins/**")
				.hasRole("ADMIN")
				.and()
				.formLogin().loginPage("/showMyloginPage")
				.loginProcessingUrl("/AthunticatedUser").permitAll()
				.and()
				.logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");

	}

}
