package matheus.github.task.services.implementation.login;

import matheus.github.task.dto.authdto.AuthDTO;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.jwt.JwtService;
import matheus.github.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	public String getTokenByAuthentication(AuthDTO authDTO) throws UserNotFoundException {
		if (!userRepository.existsByUsername(authDTO.getUsername())) {
			throw new UserNotFoundException(String.format("User not found by provided username: %s", authDTO.getUsername()));
		}

		Authentication authenticated = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
		);

		return jwtService.getToken(authenticated.getName());
	}
}
