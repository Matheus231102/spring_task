package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.implementation.register.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PathConstants.REGISTER_URI_PATH)
public class RegisterController {

    @Autowired
    private RegisterServiceImpl registerService;

    @PostMapping
    public UserRDTO registerUser(@RequestBody @Valid UserDTO userDTO) {
	   return registerService.registerUser(userDTO);
    }
}
