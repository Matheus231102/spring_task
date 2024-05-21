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
public class ResouceDateFilterController extends AuthenticationContext {

	@Autowired
	private ResourceManager resourceManager;

	@GetMapping("/tasks/conclusion/between")
	public ResponseEntity<List<TaskRDTO>> getTasksByDate(@RequestParam LocalDateTime date1,
														 @RequestParam LocalDateTime date2) throws UserNotFoundException {
		if (date1.isAfter(date2)) {
			LocalDateTime temp = date1;
			date1 = date2;
			date2 = temp;
		}

		Specification<TaskEntity> specification = withConclusionDateUnderOrEqual(date2)
				.and(withConclusionDateOverOrEqual(date1));

		List<TaskRDTO> taskRDTOList = resourceManager.getAllTasksSpecification(
				getAuthenticatedUsername(), specification);

		return ResponseEntity
				.ok()
				.body(sorted(taskRDTOList));
	}

	@GetMapping("/tasks/conclusion/underOrEqual")
	public ResponseEntity<List<TaskRDTO>> getTasksByDateUnderOrEqual(@RequestParam LocalDateTime date) throws UserNotFoundException {
		Specification<TaskEntity> specification = withConclusionDateUnderOrEqual(date);
		List<TaskRDTO> taskRDTOList = resourceManager.getAllTasksSpecification(getAuthenticatedUsername(), specification);
		return ResponseEntity
				.ok()
				.body(sorted(taskRDTOList));
	}

	@GetMapping("/tasks/conclusion/overOrEqual")
	public ResponseEntity<List<TaskRDTO>> getTasksByDateOverOrEqual(
			@RequestParam LocalDateTime date) throws UserNotFoundException {
		Specification<TaskEntity> specification = withConclusionDateOverOrEqual(date);

		List<TaskRDTO> taskRDTOList = resourceManager.getAllTasksSpecification(getAuthenticatedUsername(), specification);
		return ResponseEntity
				.ok()
				.body(sorted(taskRDTOList));
	}



}
