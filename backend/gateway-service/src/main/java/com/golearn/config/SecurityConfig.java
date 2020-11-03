package com.golearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.golearn.security.JwtAuthorizationFilter;
import com.golearn.security.JwtProperties;
import com.golearn.security.UserAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.addFilterBefore(new JwtAuthorizationFilter(new JwtProperties()), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(HttpMethod.DELETE).authenticated()
			.antMatchers(HttpMethod.PUT).authenticated()
			.antMatchers(HttpMethod.POST).authenticated()
			.antMatchers(HttpMethod.GET, "/account/users").authenticated()
			.antMatchers(HttpMethod.GET, "/account/like").authenticated()
			.antMatchers(HttpMethod.GET, "/course/manager/**/search").authenticated()
			.antMatchers(HttpMethod.GET, "/course/video/**").authenticated()
			.antMatchers(HttpMethod.GET, "/video").authenticated()
			.antMatchers(HttpMethod.GET, "/video/like/**").authenticated()
			.antMatchers(HttpMethod.GET, "/video/record/**").authenticated()
			.antMatchers(HttpMethod.GET, "/video/save/**").authenticated()
			.antMatchers(HttpMethod.GET, "/notification/**").authenticated()
			.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
			.antMatchers(HttpMethod.POST, "/account/users").permitAll()
			.anyRequest().permitAll();
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		config.addExposedHeader("Authorization");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
