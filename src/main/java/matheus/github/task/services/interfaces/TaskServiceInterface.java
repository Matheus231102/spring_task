package matheus.github.task.services.interfaces;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.exception.exceptions.UserNotFoundException;

import java.util.List;

public interface TaskServiceInterface {
     TaskRDTO insertTask(TaskDTO taskDTO);
     TaskRDTO removeTaskById(Long id) throws TaskNotFoundException;
     TaskRDTO getTaskById(Long id) throws TaskNotFoundException;
     List<TaskRDTO> getAllTasks();
     List<TaskRDTO> insertTasks(List<TaskDTO> taskDTOList);

     List<TaskRDTO> getAllTasksByUser(UserEntity userEntity) throws UserNotFoundException;

     List<TaskRDTO> deleteAllTasksByUser(UserEntity userEntity);

     List<TaskRDTO> getAllTasksByUserAndPriority(UserEntity userEntity, EnumTaskPriority enumTaskPriority);

     List<TaskRDTO> insertTaskByUser(UserEntity userEntity, TaskDTO taskDTO);

     List<TaskRDTO> getAllTaskByUserAndTitleStartingWith(UserEntity userEntity, String startWiths);

     List<TaskRDTO> deleteByUserAndTaskId(UserEntity userEntity, Long id);
}
