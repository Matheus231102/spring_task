package matheus.github.task.services.implementation.register;

import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.exception.exceptions.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.UsernameAlreadyExistsException;
import matheus.github.task.services.implementation.user.UserServiceImpl;
import matheus.github.task.services.interfaces.RegisterServiceInterface;
import matheus.github.task.utils.EncodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterServiceInterface {

    @Autowired
    private EncodeUtils encodeUtils;
	@Autowired
	private UserServiceImpl userService;

	@Override
    public UserRDTO registerUser(UserDTO userDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		encodeUtils.encodeUserPassword(userDTO);
		return userService.insertUser(userDTO);
    }

}
