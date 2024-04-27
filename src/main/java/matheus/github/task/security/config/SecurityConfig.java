package matheus.github.task.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	   return http.authorizeHttpRequests(
			 requests -> requests
			 .anyRequest().authenticated()
			 )
			 .formLogin(withDefaults())
			 .httpBasic(withDefaults())
			 .build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
	   UserDetails admin = User
			 .withDefaultPasswordEncoder()
			 .username("admin")
			 .password("12345")
			 .authorities("admin")
			 .build();

	   UserDetails user = User
			 .withDefaultPasswordEncoder()
			 .username("user")
			 .password("12345")
			 .authorities("read")
			 .build();
	   return new InMemoryUserDetailsManager(admin, user);
    }

}
