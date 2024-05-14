package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.authdto.AuthDTO;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.jwt.JwtService;
import matheus.github.task.repositories.UserRepository;
import matheus.github.task.security.constants.PathConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
@RequestMapping(PathConstants.LOGIN_URI_PATH)
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    private ResponseEntity loginUser(@RequestBody @Valid AuthDTO authDTO) throws UserNotFoundException {
        if (!userRepository.existsByUsername(authDTO.getUsername())) {
            throw new UserNotFoundException(String.format("User not found by provided username: %s", authDTO.getUsername()));
        }

        //TODO caso a senha esteja incorreta está retornando -> "message": "Usuário inexistente ou senha inválida" o que está errado
        Authentication authenticated = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
        );

        String token = jwtService.getToken(authenticated.getName());

        //TODO verificar se usuário existe ou senha está incorreta
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
    }
}
