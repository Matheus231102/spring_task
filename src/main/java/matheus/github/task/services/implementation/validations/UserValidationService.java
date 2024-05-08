package matheus.github.task.services.implementation.validations;

import matheus.github.task.exception.exceptions.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.UsernameAlreadyExistsException;
import matheus.github.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

     @Autowired
     private UserRepository userRepository;

     public void validateUsername(String username) throws UsernameAlreadyExistsException {
          if (userRepository.existsByUsername(username)) {
               throw new UsernameAlreadyExistsException("There is already a user with the given username: " + username);
          }
     }

     public void validateEmail(String email) throws EmailAlreadyExistsException {
          if (userRepository.existsByEmail(email)) {
               throw new EmailAlreadyExistsException("There is already a user with the given e-mail: " + email);
          }
     }
}
