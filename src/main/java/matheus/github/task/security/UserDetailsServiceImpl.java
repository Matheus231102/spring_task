package matheus.github.task.security;

import matheus.github.task.entities.UserEntity;
import matheus.github.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   Optional<UserEntity> user = userRepository.findByUsername(username);
	   if (user.isPresent()) {
		  return (UserDetails) user.get();
	   }
	   throw new UsernameNotFoundException("User " + username + " not found");
    }
}
