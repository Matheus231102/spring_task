package matheus.github.task.services.implementation.register;

import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.dto.mappers.UserMapper;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.enums.EnumRole;
import matheus.github.task.repositories.UserRepository;
import matheus.github.task.services.interfaces.RegisterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserRDTO registerUser(UserDTO userDTO) {
	   UserEntity userEntity = userMapper.toEntity(userDTO);
	   encodeUserPassword(userEntity);
	   setDefaultUserRole(userEntity);
	   userRepository.save(userEntity);
	   return userMapper.toRDTO(userEntity);
    }

    public void encodeUserPassword(UserEntity userEntity) {
	   String hashedPassword = passwordEncoder.encode(userEntity.getPassword());
	   userEntity.setPassword(hashedPassword);
    }

    public void setDefaultUserRole(UserEntity userEntity) {
	   userEntity.setRole(EnumRole.USER);
    }
}
