package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	
	@Autowired
	private UserDetailsService service;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(service).passwordEncoder(encoder);
		/*auth.jdbcAuthentication().dataSource(ds)
		.passwordEncoder(new BCryptPasswordEncoder())
		.authoritiesByUsernameQuery("select uname,role from user_roles where uname=?")
		.usersByUsernameQuery("select uname,pwd,status from users where uname=?");*/
		//auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("raja").password("$2a$10$dw7bAAouP/WjoMo0nF73q.ynfXhH7aqImK2wzMvL8/2QHCu61iruS").roles("CUSTOMER");
		//auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("rajesh").password("$2a$10$8sgEzWDqWQkjO4OS2Nbmne8tEhQI8wl9oxDND79YTlWL4kGgdp7jK").roles("MANAGER");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/register","/user/showLogin").permitAll()
		.antMatchers("/bank/").permitAll()
		
		.antMatchers("/bank/offers").authenticated()
		.antMatchers("/bank/balance").hasAnyAuthority("CUSTOMER","MANAGER")
		.antMatchers("/bank/loanApprove").hasAnyAuthority("MANAGER")
		.anyRequest().authenticated()
		.and().formLogin().defaultSuccessUrl("/bank/", true)
		.loginPage("/user/showLogin")
		.loginProcessingUrl("/login")
		.failureUrl("/user/showLogin?error")
		//.and().httpBasic()
		.and().formLogin()
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
		.logoutSuccessUrl("/user/showLogin?logout")
		.and().rememberMe()
		
		.and().exceptionHandling().accessDeniedPage("/bank/denied")
		.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
	}
	
	/*@Bean
	protected InMemoryUserDetailsManager userDetailsService()
	{
		UserDetails user = User.withDefaultPasswordEncoder()
	            .username("user")
	            .password("password")
	            .roles("USER")
	            .build();
	        return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		
		return http.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/offers").authenticated()
		.antMatchers("/balance").hasAnyRole("CUSTOMER","MANAGER")
		.antMatchers("/loanApprove").hasAnyRole("MANAGER")
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().exceptionHandling().accessDeniedPage("/denied").and().build();
	}*/
	

}
