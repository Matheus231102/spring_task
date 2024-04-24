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
     public List<TaskRDTO> getAllTasksByUserId(Long id) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserById(id);
          return taskService.getAllTasksByUser(userMapper.toEntity(userRDTO));
     }

     @Override
     public List<TaskRDTO> getAllTasksByUserIdAndPriority(Long id, EnumTaskPriority enumTaskPriority) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserById(id);
          return taskService.getAllTasksByUserAndPriority(userMapper.toEntity(userRDTO), enumTaskPriority);
     }

     @Override
     public void deleteAllTasksByUserId(Long id) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserById(id);
          taskService.deleteAllTasksByUser(userMapper.toEntity(userRDTO));
     }

     public List<TaskRDTO> insertTaskByUserId(Long id, TaskDTO taskDTO) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserById(id);
          return taskService.insertTaskByUser(userMapper.toEntity(userRDTO), taskDTO);
     }

}
