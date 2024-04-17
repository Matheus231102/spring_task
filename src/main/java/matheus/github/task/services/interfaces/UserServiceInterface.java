package matheus.github.task.services.interfaces;

import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;

import java.util.List;

public interface UserServiceInterface {
     UserRDTO insertUser(UserDTO userDTO);
     UserRDTO removeUserById(Long id);
     UserRDTO  getUserById(Long id);
     UserRDTO getUserByUsername(String username);
     List<UserRDTO> getAllUsers();
     List<UserRDTO> insertUsers(List<UserDTO> userDTOList);
}
