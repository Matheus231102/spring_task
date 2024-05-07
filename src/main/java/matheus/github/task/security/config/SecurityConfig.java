package matheus.github.task.security.config;

import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.security.filters.ValidateJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private ValidateJwtFilter validateJwtFilter;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	   return http
			 .csrf(csrf -> csrf.disable())
			 .cors(cors -> cors.disable())
			 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			 .authorizeHttpRequests(
			 requests -> requests
				    .requestMatchers(PathConstants.REGISTER_URI_PATH).permitAll()
				    .requestMatchers(PathConstants.LOGIN_URI_PATH).permitAll()
				    .requestMatchers(PathConstants.ALL_RESOURCES_URI_PATH).hasAnyRole("ADMIN", "USER", "MANAGER")
				    .requestMatchers(PathConstants.ALL_USER_URI_PATH).hasAnyRole("ADMIN", "MANAGER")
				    .requestMatchers(PathConstants.ALL_TASK_URI_PATH).hasAnyRole("ADMIN", "MANAGER")
				    .anyRequest().authenticated()
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

}
