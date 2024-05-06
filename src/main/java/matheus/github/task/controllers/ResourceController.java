package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.services.implementation.ResourceManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/resource")
public class ResourceController {

     @Autowired
     private ResourceManagerImpl resourceManager;

     private String getAuthenticatedUsername() {
          Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          if (principal instanceof UserDetails) {
               return ((UserDetails) principal).getUsername();
          } else {
               return principal.toString();
          }
     }

     @GetMapping(path = "/tasks")
     public List<TaskRDTO> getTasksByUser() throws UserNotFoundException {
          return resourceManager.getAllTasksByUsername(getAuthenticatedUsername());
     }

     @GetMapping(path = "/tasks/priority/{priority}")
     public List<TaskRDTO> getTasksByUserAndPriority(@PathVariable(name = "priority") EnumTaskPriority priority) throws UserNotFoundException {
          return resourceManager.getAllTasksByUsernameAndPriority(getAuthenticatedUsername(), priority);
     }

     @PostMapping(path = "/tasks")
     public List<TaskRDTO> insertTaskByUser(@RequestBody @Valid TaskDTO taskDTO) throws UserNotFoundException {
          return resourceManager.insertTaskByUsername(getAuthenticatedUsername(), taskDTO);
     }

     @DeleteMapping(path = "/tasks")
     public void deleteTasksByUser() throws UserNotFoundException {
          resourceManager.deleteAllTasksByUsername(getAuthenticatedUsername());
     }

     @GetMapping(path = "/tasks/title_starting/{starts}")
     public List<TaskRDTO> getTasksByUserAndTitleStartingWith(@PathVariable(name = "starts") String startsWith) throws UserNotFoundException {
          return resourceManager.getAllTasksByUsernameAndTitleStartingWith(getAuthenticatedUsername() , startsWith);
     }

     @DeleteMapping(path = "/tasks/{taskId}")
     public List<TaskRDTO> deleteTaskByUserAndTaskId(@PathVariable(name = "taskId") UUID taskId)
             throws UserNotFoundException {
          return resourceManager.deleteTaskByUsernameAndTaskId(getAuthenticatedUsername(), taskId);
     }

     @GetMapping(path = "/users")
     public UserRDTO getUserInfoByUser() throws UserNotFoundException {
          return resourceManager.getUserByUsername(getAuthenticatedUsername());
     }

}
