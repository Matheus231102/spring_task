package matheus.github.task.services.interfaces;

import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.exception.exceptions.data_conflict_exception.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.data_conflict_exception.UsernameAlreadyExistsException;

import java.util.List;

public interface UserServiceInterface {
     UserRDTO insertUser(UserDTO userDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;
     List<UserRDTO> insertUsers(List<UserDTO> userDTOList) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;
     UserRDTO removeUserById(Long id) throws UserNotFoundException;
     UserRDTO  getUserById(Long id) throws UserNotFoundException;
     UserRDTO getUserByUsername(String username) throws UserNotFoundException;
     UserRDTO getUserByEmail(String username) throws UserNotFoundException;
     List<UserRDTO> getAllUsers();
}
