package com.bobst.knowledge.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private final String USERS_QUERY = "select email, password, 1 from KPI_ROLE where email=?";
	private final String ROLES_QUERY = "select email, role from KPI_ROLE where email=?";

	/**
	 * Configuration de l'authentification par JDBC
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//JDBC Authentication
		auth.jdbcAuthentication()
		.usersByUsernameQuery(USERS_QUERY)
		.authoritiesByUsernameQuery(ROLES_QUERY)
		.dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
		.antMatchers("/", "/kpi", "/configuration").permitAll()
		.antMatchers("/createUser", "/updateUser", "/deleteUser", "/createActivity", "/updateActivity").authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll() 
		.and()
		.formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/login").usernameParameter("email").passwordParameter("password")
		.and() 			
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/") 			
		.and() 
		.rememberMe()
		.and()
		.exceptionHandling().accessDeniedPage("/403");
	}
			
}
