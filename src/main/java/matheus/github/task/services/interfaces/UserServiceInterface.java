package matheus.github.task.services.interfaces;

import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.exception.exceptions.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.UsernameAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface UserServiceInterface {
     UserRDTO insertUser(UserDTO userDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;
     List<UserRDTO> insertUsers(List<UserDTO> userDTOList) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;
     UserRDTO removeUserById(UUID id) throws UserNotFoundException;
     UserRDTO  getUserById(UUID id) throws UserNotFoundException;
     UserRDTO getUserByUsername(String username) throws UserNotFoundException;
     UserRDTO getUserByEmail(String username) throws UserNotFoundException;
     List<UserRDTO> getAllUsers();
}
