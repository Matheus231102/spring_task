package matheus.github.task.services.implementation;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.dto.mappers.TaskMapper;
import matheus.github.task.entities.TaskEntity;
import matheus.github.task.repositories.TaskRepository;
import matheus.github.task.services.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskServiceInterface {

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
     public TaskRDTO removeTaskById(Long id) {
          Optional<TaskEntity> task = taskRepository.findById(id);
          if (task.isPresent()) {
               taskRepository.delete(task.get());
               return taskMapper.toRDTO(task.get());
          }
          return null;
     }

     @Override
     public TaskRDTO getTaskById(Long id) {
          Optional<TaskEntity> task = taskRepository.findById(id);
          return task.map(taskEntity -> taskMapper.toRDTO(taskEntity)).orElse(null);
     }


     @Override
     public List<TaskRDTO> getAllTasks() {
          return taskRepository.findAll().stream()
                  .map(taskEntity -> taskMapper.toRDTO(taskEntity))
                  .toList();
     }
}
