package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.authdto.AuthDTO;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.jwt.JwtService;
import matheus.github.task.security.constants.PathConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PathConstants.LOGIN_URI_PATH)
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    private ResponseEntity loginUser(@RequestBody @Valid AuthDTO authDTO) throws UserNotFoundException {
        try {
            Authentication authenticated = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
            );

            String token = jwtService.getToken(authenticated.getName());

            return ResponseEntity.status(HttpStatus.OK).header("Authorization", token).build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
