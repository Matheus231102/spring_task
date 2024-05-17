package matheus.github.task.controllers;

import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.entities.TaskEntity;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.security.AuthenticationContext;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.implementation.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static matheus.github.task.repositories.spec.TaskSpecs.withConclusionDateOverOrEqual;
import static matheus.github.task.repositories.spec.TaskSpecs.withConclusionDateUnderOrEqual;
import static matheus.github.task.utils.TaskUtils.sorted;

@RestController
@RequestMapping(PathConstants.DEFAULT_TASKS_RESOURCE)
public class ResouceDateFilterController {

	@Autowired
	private ResourceManager resourceManager;

	@Autowired
	private AuthenticationContext authenticationContext;

	@GetMapping("/tasks/conclusion/between")
	public ResponseEntity<List<TaskRDTO>> getTasksByDate(@RequestParam("date1") LocalDateTime mindate,
														 @RequestParam("date2") LocalDateTime maxdate) throws UserNotFoundException {
		String authenticatedUsername = authenticationContext.getAuthenticatedUsername();

		if (mindate.isAfter(maxdate)) {
			LocalDateTime temp = mindate;
			mindate = maxdate;
			maxdate = temp;
		}

		Specification<TaskEntity> specification = withConclusionDateUnderOrEqual(maxdate)
				.and(withConclusionDateOverOrEqual(mindate));

		List<TaskRDTO> taskRDTOList = resourceManager.getAllTasksSpecification(
				authenticatedUsername, specification);

		return ResponseEntity
				.ok()
				.body(sorted(taskRDTOList));
	}

	@GetMapping("/tasks/conclusion/underOrEqual")
	public ResponseEntity<List<TaskRDTO>> getTasksByDateUnderOrEqual(@RequestParam LocalDateTime date) throws UserNotFoundException {
		String authenticatedUsername = authenticationContext.getAuthenticatedUsername();
		Specification<TaskEntity> specification = withConclusionDateUnderOrEqual(date);

		List<TaskRDTO> taskRDTOList = resourceManager.getAllTasksSpecification(authenticatedUsername, specification);
		return ResponseEntity
				.ok()
				.body(sorted(taskRDTOList));
	}

	@GetMapping("/tasks/conclusion/overOrEqual")
	public ResponseEntity<List<TaskRDTO>> getTasksByDateOverOrEqual(
			@RequestParam LocalDateTime date) throws UserNotFoundException {
		String authenticatedUsername = authenticationContext.getAuthenticatedUsername();
		Specification<TaskEntity> specification = withConclusionDateOverOrEqual(date);

		List<TaskRDTO> taskRDTOList = resourceManager.getAllTasksSpecification(authenticatedUsername, specification);
		return ResponseEntity
				.ok()
				.body(sorted(taskRDTOList));
	}



}
