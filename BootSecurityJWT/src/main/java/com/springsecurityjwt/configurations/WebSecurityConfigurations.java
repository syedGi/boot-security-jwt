package com.springsecurityjwt.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springsecurityjwt.filters.JWTRequestFilter;
import com.springsecurityjwt.utils.JwtUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfigurations {
	
	@Autowired
	private JWTRequestFilter jwtRequestFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/register","/authentication").permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/api/**")
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
//		return httpSecurity.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/register", "/authentication").permitAll())
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/api/**")
//                        .authenticated())
//                .sessionManagement(management -> management
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
	}
	
	
	
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		return httpSecurity.csrf().disable()
//				.authorizeHttpRequests()
//				.requestMatchers("/register","/authentication").permitAll()
//				.and()
//				.authorizeHttpRequests()
//				.requestMatchers("/api/**")
//				.authenticated()
//				.and()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//				.build();
//	}
	
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//	    return httpSecurity
//	        .csrf(csrf -> csrf.disable())
//	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/token/**").permitAll()
//	            .anyRequest().authenticated()
//	        )
//	        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//	        .httpBasic(Customizer.withDefaults())
//	        .build();
//	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
