package matheus.github.task.services.interfaces;

import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.exception.exceptions.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.UsernameAlreadyExistsException;

public interface RegisterServiceInterface {
    UserRDTO registerUser(UserDTO user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;
}
