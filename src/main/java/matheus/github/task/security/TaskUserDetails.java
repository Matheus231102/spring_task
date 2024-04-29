package matheus.github.task.security;

import matheus.github.task.entities.UserEntity;
import matheus.github.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskUserDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         String userName = null;
         String password = null;
         List<GrantedAuthority> authorities = null;
         Optional<UserEntity> user = userRepository.findByUsername(username);
         if (user.isPresent()) {
              userName = user.get().getUsername();
              password = user.get().getPassword();
              authorities = new ArrayList<>();
              authorities.add(new SimpleGrantedAuthority(user.get().getRole().name()));
         } else {
              throw new UsernameNotFoundException("User not found by the provided username");
         }
          return new User(userName, password, authorities);
    }
}
