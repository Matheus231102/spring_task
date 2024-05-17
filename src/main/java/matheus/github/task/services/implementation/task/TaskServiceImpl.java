package matheus.github.task.services.implementation.task;

import matheus.github.task.dto.mappers.TaskMapper;
import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.entities.TaskEntity;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.repositories.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl {
     public final String TASK_NOT_FOUND_BY_PROVIDED_ID = "Task not found by provided id";

     @Autowired
     private TaskRepository taskRepository;

     @Autowired
     private TaskMapper taskMapper;

     public TaskRDTO insertTask(TaskDTO taskDTO) {
          TaskEntity task = taskMapper.toEntity(taskDTO);
          task = taskRepository.save(task);
          return taskMapper.toRDTO(task);
     }

     public TaskRDTO getTaskById(UUID id) throws TaskNotFoundException {
          Optional<TaskEntity> task = taskRepository.findById(id);
          if (task.isPresent()) {
               return taskMapper.toRDTO(task.get());
          }
          throw new TaskNotFoundException(TASK_NOT_FOUND_BY_PROVIDED_ID + id);
     }

     public List<TaskRDTO> getAllTasks() {
          return taskRepository.findAll()
                  .stream()
                  .map(taskEntity -> taskMapper.toRDTO(taskEntity))
                  .toList();
     }

     public List<TaskRDTO> getAllTasksByUser(UserEntity userEntity) {
          List<TaskEntity> taskEntityList = taskRepository.findByUser(userEntity);
          return taskEntityList
                  .stream()
                  .map(taskEntity -> taskMapper.toRDTO(taskEntity))
                  .collect(Collectors.toList());
     }

     public List<TaskRDTO> getTasksByUserAndFilterDate(UserEntity userEntity, LocalDateTime minDate, LocalDateTime maxDate) {
          return taskRepository.getTasksByConclusionDate(userEntity, minDate, maxDate).stream()
                  .map(taskEntity -> taskMapper.toRDTO(taskEntity))
                  .toList();
     }

     public List<TaskRDTO> insertTaskByUser(UserEntity userEntity, TaskDTO taskDTO) {
          TaskEntity taskEntity = taskMapper.toEntity(taskDTO);
          taskEntity.setUser(userEntity);
          taskRepository.save(taskEntity);

          return taskRepository.findByUser(userEntity)
                  .stream()
                  .map(entity -> taskMapper.toRDTO(entity))
                  .collect(Collectors.toList());
     }

     public List<TaskRDTO> insertTasksByUser(UserEntity userEntity, List<TaskDTO> taskDTOList) {
          List<TaskEntity> taskEntityList = taskDTOList.stream()
                  .map(taskDTO -> taskMapper.toEntity(taskDTO))
                  .peek(taskEntity -> taskEntity.setUser(userEntity))
                  .toList();

          taskEntityList = taskRepository.saveAll(taskEntityList);
          return taskMapper.taskEntityListToRDTO(taskEntityList);
     }

     public void deleteByUserAndTaskId(UserEntity userEntity, UUID id) {
          taskRepository.deleteByUserAndId(userEntity, id);
     }

     public void deleteAllTasksByUser(UserEntity userEntity) {
          taskRepository.deleteAllByUser(userEntity);
     }

     public TaskRDTO deleteTaskById(UUID id) throws TaskNotFoundException {
          Optional<TaskEntity> task = taskRepository.findById(id);
          if (task.isPresent()) {
               taskRepository.delete(task.get());
               return taskMapper.toRDTO(task.get());
          }
          throw new TaskNotFoundException(TASK_NOT_FOUND_BY_PROVIDED_ID + id);
     }

}

