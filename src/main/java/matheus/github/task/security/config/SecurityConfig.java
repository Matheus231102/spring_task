package matheus.github.task.security.config;

import matheus.github.task.security.constants.PathConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	   return http
			 .csrf(csrf -> csrf.disable())
			 .cors(cors -> cors.disable())
			 .authorizeHttpRequests(
			 requests -> requests
				    .requestMatchers(PathConstants.REGISTER_URI_PATH, PathConstants.LOGIN_URI_PATH).permitAll()
				    .requestMatchers(PathConstants.ALL_RESOURCES_URI_PATH).hasAnyRole("ADMIN", "USER", "MANAGER")
				    .requestMatchers(PathConstants.ALL_USER_URI_PATH).hasAnyRole("ADMIN", "MANAGER")
				    .requestMatchers(PathConstants.ALL_TASK_URI_PATH).hasAnyRole("ADMIN", "MANAGER")
				    .anyRequest().authenticated()
			 )
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
