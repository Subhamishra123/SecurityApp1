package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication().withUser("raja").password("{noop}rani").roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("rajesh").password("{noop}hyd").roles("MANAGER");*/
		auth.ldapAuthentication().contextSource().url("ldap://localhost:10389/o=nit")
		.managerDn("uid=admin,ou=system").managerPassword("secret")
		.and()
		.userSearchBase("ou=users").userSearchFilter("(cn={0})")
		.groupSearchBase("ou=roles").groupSearchFilter("(uniqueMember={0})")
		.groupRoleAttribute("cn").rolePrefix("ROLE_");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").authenticated()//.permitAll()
		.antMatchers("/offers").authenticated()
		.antMatchers("/balance").hasAnyRole("CUSTOMER","MANAGER")
		.antMatchers("/loanApprove").hasAnyRole("MANAGER")
		.anyRequest().authenticated()
		//.and().httpBasic()
		.and().formLogin()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
		.and().rememberMe()
		
		.and().exceptionHandling().accessDeniedPage("/denied")
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
