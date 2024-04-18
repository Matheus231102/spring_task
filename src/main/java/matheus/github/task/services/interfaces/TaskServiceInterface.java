package matheus.github.task.services.interfaces;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.exception.exceptions.TaskNotFoundException;

import java.util.List;

public interface TaskServiceInterface {
     TaskRDTO insertTask(TaskDTO taskDTO);
     TaskRDTO removeTaskById(Long id) throws TaskNotFoundException;
     TaskRDTO getTaskById(Long id) throws TaskNotFoundException;
     List<TaskRDTO> getAllTasks();
     List<TaskRDTO> insertTasks(List<TaskDTO> taskDTOList);
}
