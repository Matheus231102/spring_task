package matheus.github.task.controller;

import com.jayway.jsonpath.JsonPath;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
				.creationDate(LocalDateTime.now())
				.tasks(new ArrayList<>())
				.build();
	}

	@Test
	void givenCorrectUserId_whenGetUserById_thenReturnUser() throws Exception {
		when(userService.getUserById(userId)).thenReturn(userRDTO);
		ResultActions resultPerform = mockMvc.perform(get(PathConstants.DEFAULT_USERS_PATH + "/{userId}", userId));
		resultPerform
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(userId.toString()))
				.andExpect(jsonPath("$.username").value("Test name"))
				.andDo(print());
	}

	@Test
	void givenCorrectUserId_whenGetUserById_thenVerifyCreationDate() throws Exception {
		when(userService.getUserById(userId)).thenReturn(userRDTO);
		ResultActions resultPerform = mockMvc.perform(get(PathConstants.DEFAULT_USERS_PATH + "/{userId}", userId));

		resultPerform
				.andExpect(jsonPath("$.id").value(userId.toString()))
				.andExpect(jsonPath("$.username").value("Test name"));

		String jsonResponse = resultPerform.andReturn().getResponse().getContentAsString();
		List<Integer> dateList = JsonPath.parse(jsonResponse).read("$.creationDate");

		LocalDateTime caughtLocalDateTime = fromList(dateList);

		Assertions.assertInstanceOf(LocalDateTime.class, caughtLocalDateTime);
	}

	@Test
	void givenWrongUserId_whenGetUserById_thenThrowException() throws Exception {
		when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("User not found"));
		var resultPerform = mockMvc.perform(
				get(PathConstants.DEFAULT_USERS_PATH + "/{userId}", userId));
		resultPerform
				.andExpect(status().isNotFound())
				.andExpect(result -> Assertions.assertInstanceOf(UserNotFoundException.class, result.getResolvedException()))
				.andExpect(result -> Assertions.assertEquals(result.getResolvedException().getMessage(), "User not found"))
				.andDo(print());
	}

	@Test
	void givenBadUUID_whenGetUserById_thenThrowException() throws Exception {
		String invalidUUID = "invalidUUID";
		ResultActions resultPerform = mockMvc.perform(
				get(PathConstants.DEFAULT_USERS_PATH + "/{userId}", invalidUUID));
		resultPerform
				.andExpect(status().isBadRequest())
				.andExpect(result -> Assertions.assertInstanceOf(MethodArgumentTypeMismatchException.class, result.getResolvedException()))
				.andDo(print());
	}

	public LocalDateTime fromList(List<Integer> dateList) {
		if (dateList == null || dateList.size() < 7) {
			throw new IllegalArgumentException("Invalid dateList. It must contain at least 7 elements.");
		}
		return LocalDateTime.of(
				dateList.get(0),
				dateList.get(1),
				dateList.get(2),
				dateList.get(3),
				dateList.get(4),
				dateList.get(5),
				dateList.get(6)
		);
	}

}
