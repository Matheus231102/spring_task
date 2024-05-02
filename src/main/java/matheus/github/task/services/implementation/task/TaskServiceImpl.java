package matheus.github.task.services.implementation.task;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.dto.mappers.TaskMapper;
import matheus.github.task.entities.TaskEntity;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.enums.EnumTaskStatus;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.repositories.TaskRepository;
import matheus.github.task.services.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskServiceInterface {
     public final String TASK_NOT_FOUND_BY_PROVIDED_ID = "Task not found by provided id";

     @Autowired
     private TaskRepository taskRepository;

     @Autowired
     private TaskMapper taskMapper;

     @Override
     @Transactional
     public TaskRDTO insertTask(TaskDTO taskDTO) {
          TaskEntity task = taskMapper.toEntity(taskDTO);
          task = taskRepository.save(task);
          return taskMapper.toRDTO(task);
     }

     @Override
     @Transactional
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
          return taskRepository.findAll()
                  .stream()
                  .map(taskEntity -> taskMapper.toRDTO(taskEntity))
                  .toList();
     }

     @Override
     public List<TaskRDTO> insertTasks(List<TaskDTO> taskDTOList) {
          List<TaskEntity> taskEntityList = taskMapper.taskDTOListToEntity(taskDTOList);
          taskEntityList = taskRepository.saveAll(taskEntityList);
          return taskMapper.taskEntityListToRDTO(taskEntityList);
     }


     @Override
     @Transactional
     public void deleteAllTasksByUser(UserEntity userEntity) {
          taskRepository.deleteAllByUser(userEntity);
     }

     @Override
     public List<TaskRDTO> getAllTasksByUser(UserEntity userEntity) {
          List<TaskEntity> taskEntityList = taskRepository.findByUser(userEntity);
          return taskEntityList
                  .stream()
                  .map(taskEntity -> taskMapper.toRDTO(taskEntity))
                  .collect(Collectors.toList());
     }

     @Override
     public List<TaskRDTO> getAllTasksByUserAndPriority(UserEntity userEntity, EnumTaskPriority enumTaskPriority) {
          return taskRepository.findByUserAndPriority(userEntity, enumTaskPriority)
                  .stream()
                  .map(taskEntity -> taskMapper.toRDTO(taskEntity))
                  .collect(Collectors.toList());
     }

     @Override
     @Transactional
     public List<TaskRDTO> insertTaskByUser(UserEntity userEntity, TaskDTO taskDTO) {
          TaskEntity taskEntity = taskMapper.toEntity(taskDTO);
          taskEntity.setUser(userEntity);
          taskRepository.save(taskEntity);

          return taskRepository.findByUser(userEntity)
                  .stream()
                  .map(entity -> taskMapper.toRDTO(entity))
                  .collect(Collectors.toList());
     }

     @Override
     public List<TaskRDTO> getAllTaskByUserAndTitleStartingWith(UserEntity userEntity, String startsWith) {
          return taskRepository.findByUserAndTitleStartingWith(userEntity, startsWith)
                  .stream()
                  .map(entity -> taskMapper.toRDTO(entity))
                  .collect(Collectors.toList());
     }
}
