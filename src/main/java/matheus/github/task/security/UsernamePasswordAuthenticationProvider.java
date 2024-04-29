package matheus.github.task.security;

import matheus.github.task.entities.UserEntity;
import matheus.github.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	   String username = authentication.getName();
	   String password = authentication.getCredentials().toString();
	   Optional<UserEntity> user = userRepository.findByUsername(username);
	   if (user.isPresent()) {
		  if (passwordEncoder.matches(password, user.get().getPassword())) {
			 List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			 authorities.add(new SimpleGrantedAuthority(user.get().getRole().name()));
			 return new UsernamePasswordAuthenticationToken(user.get().getUsername(), user.get().getPassword(), authorities);
		  }
		  throw new BadCredentialsException("Bad credentials");
	   }
	   throw new UsernameNotFoundException("User not found by provided username");
    }

    @Override
    public boolean supports(Class<?> authentication) {
	   return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
