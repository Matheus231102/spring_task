package matheus.github.task.dto.mappers;

import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

     @Autowired
     private ModelMapper modelMapper;

     public UserRDTO toRDTO(UserEntity userEntity) {
          return modelMapper.map(userEntity, UserRDTO.class);
     }

     public UserDTO toDTO(UserEntity userEntity) {
          return modelMapper.map(userEntity, UserDTO.class);
     }

     public UserEntity toEntity(UserDTO userDTO) {
          return modelMapper.map(userDTO, UserEntity.class);
     }

     public UserEntity toEntity(UserRDTO userRDTO) {
          return modelMapper.map(userRDTO, UserEntity.class);
     }

}
