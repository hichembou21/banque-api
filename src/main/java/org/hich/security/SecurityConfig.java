package org.hich.security;

import org.hich.security.filter.JWTAuthenticationFilter;
import org.hich.security.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin();
		http.csrf().disable();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests().antMatchers("/login/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/operations/**").hasAnyAuthority("ADMIN","EMPLOYE");
//		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/operations/debiter/**").hasAnyAuthority("ADMIN","EMPLOYE");
//		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/operations/crediter/**").hasAnyAuthority("ADMIN","EMPLOYE");
//		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/operations/virement/**").hasAnyAuthority("ADMIN","EMPLOYE");

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/clients/**").hasAnyAuthority("ADMIN","EMPLOYE");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/employes/**").hasAnyAuthority("ADMIN");

		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
