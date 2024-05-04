package matheus.github.task.services.interfaces;

import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;

public interface RegisterServiceInterface {
    UserRDTO registerUser(UserDTO user);
}
