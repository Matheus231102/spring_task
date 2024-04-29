package matheus.github.task.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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
				    .requestMatchers("/register", "/login").permitAll()
				    .anyRequest().authenticated()
			 )
			 .formLogin(withDefaults())
			 .httpBasic(withDefaults())
			 .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
    }

}
