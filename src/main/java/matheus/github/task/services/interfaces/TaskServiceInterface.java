package matheus.github.task.services.interfaces;

import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.exception.exceptions.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface TaskServiceInterface {
     TaskRDTO insertTask(TaskDTO taskDTO);
     TaskRDTO deleteTaskById(UUID id) throws TaskNotFoundException;

     TaskRDTO getTaskById(UUID id) throws TaskNotFoundException;
     List<TaskRDTO> getAllTasks();
     List<TaskRDTO> insertTasks(List<TaskDTO> taskDTOList);

     List<TaskRDTO> getAllTasksByUser(UserEntity userEntity) throws UserNotFoundException;

     void deleteAllTasksByUser(UserEntity userEntity);
     void deleteByUserAndTaskId(UserEntity userEntity, UUID id);

     List<TaskRDTO> insertTaskByUser(UserEntity userEntity, TaskDTO taskDTO);
     List<TaskRDTO> insertTasksByUser(UserEntity userEntity, List<TaskDTO> taskDTOList);
}
