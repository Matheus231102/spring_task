package matheus.github.task.services.interfaces;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;

import java.util.List;

public interface TaskServiceInterface {
     TaskRDTO insertTask(TaskDTO taskDTO);
     TaskRDTO removeTaskById(Long id);
     TaskRDTO getTaskById(Long id);
     List<TaskRDTO> getAllTasks();
}
