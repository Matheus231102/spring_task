package matheus.github.task.security.config;

import matheus.github.task.security.AuthenticationContext;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.security.filters.ValidateJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private ValidateJwtFilter validateJwtFilter;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	   return http
			 .csrf(csrf -> csrf.disable())
			 .cors(withDefaults())
			 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			 .authorizeHttpRequests(
			 requests -> requests
				    .requestMatchers(PathConstants.REGISTER_URI_PATH).permitAll()
				    .requestMatchers(PathConstants.LOGIN_URI_PATH).permitAll()
				    .requestMatchers(PathConstants.ALL_RESOURCES_URI_PATH).hasRole("USER")
				    .requestMatchers(PathConstants.ALL_USERS_URI_PATH).hasRole("ADMIN")
				    .requestMatchers(PathConstants.ALL_TASKS_URI_PATH).hasRole("ADMIN")
				    .anyRequest().denyAll()
			 )
			 .addFilterBefore(validateJwtFilter, BasicAuthenticationFilter.class)
			 .httpBasic(withDefaults())
			 .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	   return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
    }

	@Bean
	public AuthenticationContext authenticationContext() {
		return new AuthenticationContext();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:4200"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION));
		configuration.setExposedHeaders(List.of("Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
