package matheus.github.task.services;

import matheus.github.task.dto.mappers.UserMapper;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.repositories.UserRepository;
import matheus.github.task.services.implementation.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void testGetUserById_usersExists() throws UserNotFoundException {
		UUID userId = UUID.randomUUID();
		UserEntity userEntity = new UserEntity();
		UserRDTO expectedUserRDTO = new UserRDTO();

		when(userRepository.findById(userId))
				.thenReturn(Optional.of(userEntity));

		when(userMapper.toRDTO(userEntity))
				.thenReturn(expectedUserRDTO);

		UserRDTO result = userService.getUserById(userId);

		assertEquals(expectedUserRDTO, result);
	}

	@Test
	void testGetUserById_userNotExists() {
		UUID userId = UUID.randomUUID();
		UserEntity userEntity = new UserEntity();
		UserRDTO expectedUserRDTO = new UserRDTO();

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
	}

	@Test
	void testRemoveUserById() {
		UUID userId = UUID.randomUUID();
		UserEntity userEntity = new UserEntity();

		when(userRepository.findById(userId))
					.thenReturn(Optional.of(userEntity));

		assertDoesNotThrow(() -> {
			userService.removeUserById(userId);
		});
	}

	@Test
	void testRemoveUserById_userNotExists() {
		UUID userId = UUID.randomUUID();

		when(userRepository.findById(userId))
				.thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> {
			userService.removeUserById(userId);
		});
	}


}
