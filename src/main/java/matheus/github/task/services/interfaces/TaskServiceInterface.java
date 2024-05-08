package matheus.github.task.services.interfaces;

import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.exception.exceptions.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface TaskServiceInterface {
     TaskRDTO insertTask(TaskDTO taskDTO);
     TaskRDTO removeTaskById(UUID id) throws TaskNotFoundException;
     TaskRDTO getTaskById(UUID id) throws TaskNotFoundException;
     List<TaskRDTO> getAllTasks();
     List<TaskRDTO> insertTasks(List<TaskDTO> taskDTOList);

     List<TaskRDTO> getAllTasksByUser(UserEntity userEntity) throws UserNotFoundException;
     List<TaskRDTO> deleteAllTasksByUser(UserEntity userEntity);
     List<TaskRDTO> getAllTasksByUserAndPriority(UserEntity userEntity, EnumTaskPriority enumTaskPriority);
     List<TaskRDTO> insertTaskByUser(UserEntity userEntity, TaskDTO taskDTO);
     List<TaskRDTO> getAllTaskByUserAndTitleStartingWith(UserEntity userEntity, String startWiths);
     List<TaskRDTO> deleteByUserAndTaskId(UserEntity userEntity, UUID id);
}
