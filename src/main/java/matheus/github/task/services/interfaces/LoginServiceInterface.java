package matheus.github.task.services.interfaces;

import matheus.github.task.dto.AuthDTO;
import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;

public interface LoginServiceInterface {
    String loginUser(AuthDTO authDTO);
}
