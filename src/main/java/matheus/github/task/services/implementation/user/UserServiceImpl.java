package matheus.github.task.services.implementation.user;

import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;
import matheus.github.task.dto.mappers.UserMapper;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.exception.exceptions.data_conflict_exception.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.data_conflict_exception.UsernameAlreadyExistsException;
import matheus.github.task.repositories.UserRepository;
import matheus.github.task.services.implementation.validations.UserValidationService;
import matheus.github.task.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface {
     public final String USER_NOT_FOUND_BY_PROVIDED_ID = "User not found by provided id";
     public final String USER_NOT_FOUND_BY_PROVIDED_USERNAME = "User not found by provided username";
     private final String USER_NOT_FOUND_BY_PROVIDED_EMAIL = "User not found by provided e-mail";

     @Autowired
     private UserRepository userRepository;

     @Autowired
     private UserMapper userMapper;

     @Autowired
     private UserValidationService userValidation;

     @Override
     public UserRDTO insertUser(UserDTO userDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
          userValidation.validateUsername(userDTO.getUsername());
          userValidation.validateEmail(userDTO.getEmail());
          UserEntity user = userMapper.toEntity(userDTO);
          user = userRepository.save(user);
          return userMapper.toRDTO(user);
     }

     @Override
     public UserRDTO removeUserById(Long id) throws UserNotFoundException {
          Optional<UserEntity> user = userRepository.findById(id);
          if (user.isPresent()) {
               userRepository.delete(user.get());
               return userMapper.toRDTO(user.get());
          }
          throw new UserNotFoundException(USER_NOT_FOUND_BY_PROVIDED_ID);
     }

     @Override
     public UserRDTO getUserById(Long id) throws UserNotFoundException {
          Optional<UserEntity> user = userRepository.findById(id);
          if (user.isPresent()) {
               return userMapper.toRDTO(user.get());
          }
          throw new UserNotFoundException(USER_NOT_FOUND_BY_PROVIDED_ID);
     }

     @Override
     public UserRDTO getUserByUsername(String username) throws UserNotFoundException {
          Optional<UserEntity> user = userRepository.findByUsername(username);
          if (user.isPresent()) {
               return userMapper.toRDTO(user.get());
          }
          throw new UserNotFoundException(USER_NOT_FOUND_BY_PROVIDED_USERNAME);
     }

     @Override
     public UserRDTO getUserByEmail(String email) throws UserNotFoundException {
          Optional<UserEntity> user = userRepository.findByEmail(email);
          if (user.isPresent()) {
               return userMapper.toRDTO(user.get());
          }
          throw new UserNotFoundException(USER_NOT_FOUND_BY_PROVIDED_EMAIL);
     }

     @Override
     public List<UserRDTO> getAllUsers() {
          return userRepository.findAll().stream()
                  .map(userEntity -> userMapper.toRDTO(userEntity))
                  .toList();
     }

     @Override
     public List<UserRDTO> insertUsers(List<UserDTO> userDTOList) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
          for (UserDTO userDTO : userDTOList) {
               userValidation.validateEmail(userDTO.getEmail());
               userValidation.validateUsername(userDTO.getUsername());
          }

          return userDTOList.stream()
                  .map(userDTO -> userRepository.save(userMapper.toEntity(userDTO)))
                  .map(userEntity -> userMapper.toRDTO(userEntity))
                  .toList();
     }
}