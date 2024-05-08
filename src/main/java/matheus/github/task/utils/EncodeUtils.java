package matheus.github.task.utils;

import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncodeUtils {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void encodeUserPassword(UserDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
    }

    public void encodeUserPassword(UserEntity userEntity) {
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
    }

}
