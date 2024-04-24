package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.services.implementation.ResourceManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/resource")
public class ResourceController {

     @Autowired
     private ResourceManagerImpl resourceManager;

     @GetMapping(path = "/tasks/{userid}")
     public List<TaskRDTO> getTasksByUser(@PathVariable(name = "userid") Long userid) throws UserNotFoundException {
          return resourceManager.getAllTasksByUserId(userid);
     }

     @GetMapping(path = "/tasks/{userid}/priority/{priority}")
     public List<TaskRDTO> getTasksByUserAndPriority(
             @PathVariable(name = "userid") Long userid,
             @PathVariable(name = "priority") String priority) throws UserNotFoundException {
          return resourceManager.getAllTasksByUserIdAndPriority(userid, EnumTaskPriority.valueOf(priority.toUpperCase()));
     }

     @PostMapping(path = "/tasks/{userid}")
     public List<TaskRDTO> insertTaskByUser(
             @PathVariable(name = "userid") Long userid,
             @RequestBody @Valid TaskDTO taskDTO) throws UserNotFoundException {
          return resourceManager.insertTaskByUserId(userid, taskDTO);
     }

     @DeleteMapping(path = "/tasks/{userid}")
     public void deleteTasksByUser(@PathVariable(name = "userid") Long userid) throws UserNotFoundException {
          resourceManager.deleteAllTasksByUserId(userid);
     }
}
