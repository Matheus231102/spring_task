package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.AuthDTO;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.implementation.login.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = PathConstants.LOGIN_URI_PATH)
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping
    private String loginUser(@RequestBody @Valid AuthDTO authDTO) {
	   return loginService.loginUser(authDTO);
    }

}
