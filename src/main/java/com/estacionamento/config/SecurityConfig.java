package com.estacionamento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.estacionamento.repository.UsuarioRepository;
import com.estacionamento.security.JWTAuthenticationFilter;
import com.estacionamento.security.JWTAuthorizationFilter;
import com.estacionamento.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UsuarioRepository cliRepo;

	private static final String[] PUBLIC_MATCHERS = { "/v3/api-docs/**", "/swagger-ui/**", 
			"/swagger-ui.html/**", "/documentacao/**", "/carros/**", "/clientes/**", 
			"/estacionamento/**", "/usuarios/**" };
	private static final String[] PUBLIC_MATCHERS_POST = { "/v3/api-docs/**", "/swagger-ui/**", 
			"/swagger-ui.html/**", "/documentacao/**", "/carros/**", "/clientes/**", 
			"/estacionamento/**", "/usuarios/**" };
	private static final String[] PUBLIC_MATCHERS_PUT = { "/v3/api-docs/**", "/swagger-ui/**", 
			"/swagger-ui.html/**", "/documentacao/**", "/carros/**", "/clientes/**", 
			"/estacionamento/**", "/usuarios/**" };
	private static final String[] PUBLIC_MATCHERS_DELETE = { "/v3/api-docs/**", "/swagger-ui/**", 
			"/swagger-ui.html/**", "/documentacao/**", "/carros/**", "/clientes/**", 
			"/estacionamento/**", "/usuarios/**" };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil, cliRepo));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
				.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
				.antMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_PUT).permitAll()
				.antMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS_DELETE).permitAll()
				.anyRequest().authenticated();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
