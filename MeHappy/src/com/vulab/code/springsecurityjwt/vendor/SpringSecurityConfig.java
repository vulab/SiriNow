package com.vulab.code.springsecurityjwt.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vulab.code.springsecurityjwt.auth.jwt.StatelessAuthenticationFilter;
import com.vulab.code.springsecurityjwt.auth.jwt.TokenAuthenticationService;

@Configuration
@EnableWebSecurity
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private  UserDetailsService userService;
	@Autowired
	private  TokenAuthenticationService tokenAuthenticationService;

	
	public SpringSecurityConfig() {
		super(true);	
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.exceptionHandling().and().anonymous().and().servletApi().and().headers().cacheControl().disable().and()
				.authorizeRequests().antMatchers("/").permitAll().antMatchers("/favicon.ico").permitAll()
				.antMatchers("/**/*.html").permitAll().antMatchers("/**/*.css").permitAll().antMatchers("/**/*.js")
				.permitAll().antMatchers("/web/login").permitAll().antMatchers("/web/login/home").permitAll()
				.antMatchers("/**/*all").authenticated().antMatchers("/rest/service/login").permitAll()
				.antMatchers("/rest/service/authenticate").permitAll()
				// Allow anonymous logins
				.antMatchers("/auth/**").permitAll()

				// All other request need to be authenticated
				.anyRequest().authenticated().and()
				// Custom Token based authentication based on the header
				// previously given to the client
				.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return userService;
	}


}
