package matheus.github.task.services.interfaces;

import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;

public interface RegisterServiceInterface {
    UserRDTO registerUser(UserDTO user);
}
