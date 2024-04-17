package matheus.github.task.dto.mappers;

import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;
import matheus.github.task.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

     public List<UserRDTO> UserEntityListToRDTO(List<UserEntity> userEntityList) {
          return userEntityList.stream()
                  .map(userEntity -> toRDTO(userEntity))
                  .collect(Collectors.toList());
     }

     public List<UserEntity> UserDTOListToEntity(List<UserDTO> userDTOList) {
          return userDTOList.stream().map(userDTO -> toEntity(userDTO))
                  .collect(Collectors.toList());
     }

}
