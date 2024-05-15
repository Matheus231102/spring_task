package matheus.github.task.services.interfaces;

import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.exception.exceptions.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ResouceManagerInterface {
     List<TaskRDTO> getAllTasksByUsername(String username) throws UserNotFoundException;
     List<TaskRDTO> getAllTasksByUsernameAndPriority(String username, EnumTaskPriority enumTaskPriority) throws UserNotFoundException;
     List<TaskRDTO> getAllTasksByUsernameAndTitleStartingWith(String username, String titleStartingWith) throws UserNotFoundException;

     void deleteAllTasksByUsername(String username) throws UserNotFoundException;
     void deleteTaskByUsernameAndTaskId(String username, UUID taskId) throws UserNotFoundException, TaskNotFoundException;

     List<TaskRDTO> insertTaskByUsername(String username, TaskDTO taskDTO) throws UserNotFoundException;
     List<TaskRDTO> insertTasksByUsername(String username, List<TaskDTO> taskDTOList) throws UserNotFoundException;
}
