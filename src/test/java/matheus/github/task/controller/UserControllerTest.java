package matheus.github.task.controller;

import matheus.github.task.controllers.UserController;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.exception.global.GlobalExceptionManager;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.interfaces.UserServiceInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserServiceInterface userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;
	private UUID userId;
	private UserRDTO userRDTO;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.alwaysExpect(content().contentType("application/json"))
				.setControllerAdvice(new GlobalExceptionManager())
				.build();

		userId = UUID.randomUUID();
		userRDTO = UserRDTO.builder()
				.id(userId)
				.username("Test name")
				.build();
	}

	@Test
	void givenCorrectUserId_whenGetUserById_thenReturnUser() throws Exception {
		when(userService.getUserById(userId)).thenReturn(userRDTO);
		mockMvc.perform(get(PathConstants.DEFAULT_USERS_PATH + "/{userId}", userId))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	void givenWrongUserId_whenGetUserById_thenThrowException() throws Exception {
		when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("User not found"));
		var resultPerform = mockMvc.perform(
				get("/users/{userId}", userId));

		resultPerform
				.andExpect(status().isNotFound())
				.andExpect(result -> Assertions.assertInstanceOf(UserNotFoundException.class, result.getResolvedException()))
				.andExpect(result -> Assertions.assertEquals(result.getResolvedException().getMessage(), "User not found"))
				.andDo(print());
	}
}
