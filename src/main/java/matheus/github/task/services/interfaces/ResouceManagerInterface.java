package matheus.github.task.services.interfaces;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.UserNotFoundException;

import java.util.List;

public interface ResouceManagerInterface {
     List<TaskRDTO> getAllTasksByUsername(String username) throws UserNotFoundException;
     void deleteAllTasksByUsername(String username) throws UserNotFoundException;
     List<TaskRDTO> getAllTasksByUsernameAndPriority(String username, EnumTaskPriority enumTaskPriority) throws UserNotFoundException;
     List<TaskRDTO> insertTaskByUsername(String username, TaskDTO taskDTO) throws UserNotFoundException;
     List<TaskRDTO> getAllTasksByUsernameAndTitleStartingWith(String username, String titleStartingWith) throws UserNotFoundException;
}
