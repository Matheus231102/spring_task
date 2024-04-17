package matheus.github.task.services.implementation;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.dto.mappers.TaskMapper;
import matheus.github.task.entities.TaskEntity;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.repositories.TaskRepository;
import matheus.github.task.services.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskServiceInterface {
     public final String TASK_NOT_FOUND_BY_PROVIDED_ID = "Task not found by provided id";


     @Autowired
     private TaskRepository taskRepository;

     @Autowired
     private TaskMapper taskMapper;

     @Override
     public TaskRDTO insertTask(TaskDTO taskDTO) {
          TaskEntity task = taskMapper.toEntity(taskDTO);
          task = taskRepository.save(task);
          return taskMapper.toRDTO(task);
     }

     @Override
     public TaskRDTO removeTaskById(Long id) throws TaskNotFoundException {
          Optional<TaskEntity> task = taskRepository.findById(id);
          if (task.isPresent()) {
               taskRepository.delete(task.get());
               return taskMapper.toRDTO(task.get());
          }
          throw new TaskNotFoundException(TASK_NOT_FOUND_BY_PROVIDED_ID);
     }

     @Override
     public TaskRDTO getTaskById(Long id) throws TaskNotFoundException {
          Optional<TaskEntity> task = taskRepository.findById(id);
          if (task.isPresent()) {
               return taskMapper.toRDTO(task.get());
          }
          throw new TaskNotFoundException(TASK_NOT_FOUND_BY_PROVIDED_ID);
     }

     @Override
     public List<TaskRDTO> getAllTasks() {
          return taskRepository.findAll().stream()
                  .map(taskEntity -> taskMapper.toRDTO(taskEntity))
                  .toList();
     }

     @Override
     public List<TaskRDTO> insertTasks(List<TaskDTO> taskDTOList) {
          List<TaskEntity> taskEntityList = taskMapper.taskDTOListToEntity(taskDTOList);
          taskEntityList = taskRepository.saveAll(taskEntityList);
          return taskMapper.taskEntityListToRDTO(taskEntityList);
     }
}
