package matheus.github.task.services.interfaces;

import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.UserNotFoundException;

import java.util.List;

public interface ResouceManagerInterface {
     List<TaskRDTO> getAllTasksByUserId(Long id) throws UserNotFoundException;
     void deleteAllTasksByUserId(Long id) throws UserNotFoundException;
     List<TaskRDTO> getAllTasksByUserIdAndPriority(Long id, EnumTaskPriority enumTaskPriority) throws UserNotFoundException;
}
