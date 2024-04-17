package matheus.github.task.services.implementation;

import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;
import matheus.github.task.dto.mappers.UserMapper;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.repositories.UserRepository;
import matheus.github.task.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserServiceInterface {

     @Autowired
     private UserRepository userRepository;

     @Autowired
     private UserMapper userMapper;

     @Override
     public UserRDTO insertUser(UserDTO userDTO) {
          UserEntity user = userMapper.toEntity(userDTO);
          user = userRepository.save(user);
          return userMapper.toRDTO(user);
     }

     @Override
     public UserRDTO removeUserById(Long id) {
          Optional<UserEntity> user = userRepository.findById(id);
          if (user.isPresent()) {
               userRepository.delete(user.get());
               return userMapper.toRDTO(user.get());
          }
          return null;
     }

     @Override
     public UserRDTO getUserById(Long id) {
          Optional<UserEntity> user = userRepository.findById(id);
          return user.map(userEntity -> userMapper.toRDTO(userEntity)).orElse(null);
     }

     @Override
     // TODO verificar se método do repository retorna optional
     public UserRDTO getUserByUsername(String username) {
          Optional<UserEntity> user = userRepository.findByUsername(username);
          return user.map(userEntity -> userMapper.toRDTO(userEntity)).orElse(null);
     }

     @Override
     public List<UserRDTO> getAllUsers() {
          return userRepository.findAll().stream()
                  .map(userEntity -> userMapper.toRDTO(userEntity))
                  .toList();
     }
}
