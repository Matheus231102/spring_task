package matheus.github.task.services.implementation.login;

import matheus.github.task.dto.AuthDTO;
import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;
import matheus.github.task.dto.mappers.UserMapper;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.repositories.UserRepository;
import matheus.github.task.security.UsernamePasswordAuthenticationProvider;
import matheus.github.task.services.interfaces.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsernamePasswordAuthenticationProvider authenticationProvider;

    @Override
    public UserRDTO registerUser(UserDTO userDTO) {
	   encodeUserPassword(userDTO);
	   UserEntity userEntity = userMapper.toEntity(userDTO);
	   userRepository.save(userEntity);
	   return userMapper.toRDTO(userEntity);
    }

    @Override
    public String loginUser(AuthDTO authDTO) {
	   return "";
    }

    public void encodeUserPassword(UserDTO userDTO) {
	   String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
	   userDTO.setPassword(hashedPassword);
    }

}