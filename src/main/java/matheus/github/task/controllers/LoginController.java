package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.authdto.AuthDTO;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.exception.exceptions.UsernameAlreadyExistsException;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.implementation.login.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstants.LOGIN_URI_PATH)
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping
    private ResponseEntity loginUser(@RequestBody @Valid AuthDTO authDTO) throws UserNotFoundException, UsernameAlreadyExistsException {
        String token = loginService.getTokenByAuthentication(authDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
    }
}
