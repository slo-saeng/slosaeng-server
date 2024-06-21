package com.server.slosaeng.global.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.server.slosaeng.global.auth.application.MemberDetailService;
import com.server.slosaeng.global.auth.filter.TokenAuthenticationFilter;
import com.server.slosaeng.global.auth.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	private final TokenProvider tokenProvider;

	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
			.requestMatchers(
				new AntPathRequestMatcher("/img/**"),
				new AntPathRequestMatcher("/css/**"),
				new AntPathRequestMatcher("/js/**")
			);
	}

	CorsConfigurationSource corsConfigurationSource() {
		return request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedHeaders(Collections.singletonList("*"));
			config.setAllowedMethods(Collections.singletonList("*"));
			config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:9999"));
			config.setAllowedOriginPatterns(Collections.singletonList("http://15.164.96.227:9999"));
			config.setAllowCredentials(true);
			return config;
		};
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.logout(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(SWAGGER_PATTERNS).permitAll()
				.requestMatchers(
					new AntPathRequestMatcher("/login"),
					new AntPathRequestMatcher("/member/**"),
					new AntPathRequestMatcher("/helper")
				).permitAll()
				.requestMatchers(
					new AntPathRequestMatcher("/access-token")
				).authenticated()
				.anyRequest().permitAll()
			)
			.build();
	}

	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter(tokenProvider);
	}

	@Bean
	public AuthenticationManager authenticationManager(
		HttpSecurity http,
		BCryptPasswordEncoder bCryptPasswordEncoder,
		MemberDetailService memberDetailService
	) throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(memberDetailService);
		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return new ProviderManager(authProvider);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private static final String[] SWAGGER_PATTERNS = {
		"/api-docs/json",
		"/api-docs/json/swagger-config",
		"/css/**",
		"/images/**",
		"/js/**",
		"/swagger-resources",
		"/swagger-resources/**",
		"/swagger-resources/configuration/security",
		"/swagger-resources/configuration/ui",
		"/swagger-ui/",
		"/swagger-ui.html",
		"/swagger-ui/favicon-16x16.png",
		"/swagger-ui/favicon-32x32.png",
		"/swagger-ui/index.css",
		"/swagger-ui/index.html",
		"/swagger-ui/swagger-ui-bundle.js",
		"/swagger-ui/swagger-ui.css",
		"/swagger-ui/swagger-ui.html",
		"/swagger-ui/swagger-ui-standalone-preset.js",
		"/swagger-ui/swagger-initializer.js",
		"/v2/api-docs/**",
		"/v3/api-docs/**"
	};
}
