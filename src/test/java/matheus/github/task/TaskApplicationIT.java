package matheus.github.task;

import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.exception.exceptions.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.exception.exceptions.UsernameAlreadyExistsException;
import matheus.github.task.services.implementation.ResourceManager;
import matheus.github.task.services.implementation.register.RegisterServiceImpl;
import matheus.github.task.services.implementation.user.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskApplicationIT {

	@Autowired
	private RegisterServiceImpl registerService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ResourceManager resourceManager;

	public UserDTO user1DTO = new UserDTO();
	public UserRDTO user1RDTO;

	public UserDTO userDTO2 = new UserDTO();

	@BeforeEach
	void setUp() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		user1DTO.setName("MATHEUS");
		user1DTO.setUsername("matheus2311");
		user1DTO.setPassword("123456");
		user1DTO.setEmail("matheus2311@gmail.com");
		this.user1RDTO = registerService.registerUser(user1DTO);

		userDTO2.setName("MATHEUS BADIA");
		userDTO2.setUsername("matheus231102");
		userDTO2.setPassword("123456789");
		userDTO2.setEmail("matheusbadia23@gmail.com");
	}

	@AfterEach
	void tearDown() throws UserNotFoundException {
		userService.removeUserById(user1RDTO.getId());
	}

	@Test
	void duplicateEntryEmailTest() throws UserNotFoundException, UsernameAlreadyExistsException, EmailAlreadyExistsException {
		userDTO2.setEmail(user1RDTO.getEmail());

		Assertions.assertThrows(EmailAlreadyExistsException.class, () -> {
			registerService.registerUser(userDTO2);
		});
	}

	@Test
	void duplicateEntryUsernameTest() throws UsernameAlreadyExistsException, EmailAlreadyExistsException, UserNotFoundException {
		userDTO2.setUsername(user1RDTO.getUsername());

		Assertions.assertThrows(UsernameAlreadyExistsException.class, () -> {
			registerService.registerUser(userDTO2);
		});
	}
}
