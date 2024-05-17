package matheus.github.task.services.implementation;

import matheus.github.task.dto.mappers.UserMapper;
import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.services.implementation.task.TaskServiceImpl;
import matheus.github.task.services.implementation.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResourceManager {

     @Autowired
     private UserServiceImpl userService;

     @Autowired
     private TaskServiceImpl taskService;

     @Autowired
     private UserMapper userMapper;
     
     public List<TaskRDTO> getAllTasksByUsername(String username) throws UserNotFoundException {
          return taskService.getAllTasksByUser(getUserEntity(username));
     }

     public List<TaskRDTO> getAllTasksSpecification(String username, Specification specification) throws UserNotFoundException {
          return taskService.getAllTasksWithSpecification(getUserEntity(username), specification);
     }

     public List<TaskRDTO> insertTaskByUsername(String username, TaskDTO taskDTO) throws UserNotFoundException {
          return taskService.insertTaskByUser(getUserEntity(username), taskDTO);
     }
     
     public List<TaskRDTO> insertTasksByUsername(String username, List<TaskDTO> taskDTOList) throws UserNotFoundException {
          return taskService.insertTasksByUser(getUserEntity(username), taskDTOList);
     }

     public void deleteTaskByUsernameAndTaskId(String username, UUID taskId) throws UserNotFoundException {
          taskService.deleteByUserAndTaskId(getUserEntity(username), taskId);
     }
     
     public void deleteAllTasksByUsername(String username) throws UserNotFoundException {
          taskService.deleteAllTasksByUser(getUserEntity(username));
     }

     public UserRDTO getUserByUsername(String username) throws UserNotFoundException {
          return userService.getUserByUsername(username);
     }

     private UserEntity getUserEntity(String username) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserByUsername(username);
          UserEntity userEntity = userMapper.toEntity(userRDTO);
          return userEntity;
     }

}
