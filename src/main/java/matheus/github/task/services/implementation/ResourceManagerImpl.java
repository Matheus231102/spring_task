package matheus.github.task.services.implementation;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.dto.UserRDTO;
import matheus.github.task.dto.mappers.TaskMapper;
import matheus.github.task.dto.mappers.UserMapper;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.services.implementation.task.TaskServiceImpl;
import matheus.github.task.services.implementation.user.UserServiceImpl;
import matheus.github.task.services.interfaces.ResouceManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceManagerImpl implements ResouceManagerInterface {

     @Autowired
     private UserServiceImpl userService;

     @Autowired
     private TaskServiceImpl taskService;

     @Autowired
     private TaskMapper taskMapper;

     @Autowired
     private UserMapper userMapper;

     @Override
     public List<TaskRDTO> getAllTasksByUsername(String username) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserByUsername(username);
          return taskService.getAllTasksByUser(userMapper.toEntity(userRDTO));
     }

     @Override
     public List<TaskRDTO> getAllTasksByUsernameAndPriority(String username, EnumTaskPriority enumTaskPriority) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserByUsername(username);
          return taskService.getAllTasksByUserAndPriority(userMapper.toEntity(userRDTO), enumTaskPriority);
     }

     @Override
     public void deleteAllTasksByUsername(String username) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserByUsername(username);
          taskService.deleteAllTasksByUser(userMapper.toEntity(userRDTO));
     }

     public List<TaskRDTO> insertTaskByUsername(String username, TaskDTO taskDTO) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserByUsername(username);
          return taskService.insertTaskByUser(userMapper.toEntity(userRDTO), taskDTO);
     }

     public List<TaskRDTO> getAllTasksByUsernameAndTitleStartingWith(String username, String titleStartsWith) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserByUsername(username);
          return taskService.getAllTaskByUserAndTitleStartingWith(userMapper.toEntity(userRDTO), titleStartsWith);
     }

}
