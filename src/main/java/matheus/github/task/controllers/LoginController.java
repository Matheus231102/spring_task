package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.AuthDTO;
import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;
import matheus.github.task.services.implementation.login.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping(path = "/register")
    public UserRDTO registerUser(@RequestBody @Valid UserDTO userDTO) {
	   return loginService.registerUser(userDTO);
    }

    @GetMapping(path = "/login")
    private String loginUser(@RequestBody @Valid AuthDTO authDTO) {
	   return loginService.loginUser(authDTO);
    }


}
