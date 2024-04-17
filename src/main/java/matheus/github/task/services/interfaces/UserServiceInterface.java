package matheus.github.task.services.interfaces;

import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;
import matheus.github.task.exception.exceptions.InvalidUserException;
import matheus.github.task.exception.exceptions.UserNotFoundException;

import java.util.List;

public interface UserServiceInterface {
     UserRDTO insertUser(UserDTO userDTO) throws InvalidUserException;
     UserRDTO removeUserById(Long id) throws UserNotFoundException;
     UserRDTO  getUserById(Long id) throws UserNotFoundException;
     UserRDTO getUserByUsername(String username) throws UserNotFoundException;
     List<UserRDTO> getAllUsers();
     List<UserRDTO> insertUsers(List<UserDTO> userDTOList) throws InvalidUserException;
}
