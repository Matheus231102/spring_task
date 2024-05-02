package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.services.implementation.ResourceManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/resource")
public class ResourceController {

     @Autowired
     private ResourceManagerImpl resourceManager;

     @GetMapping(path = "/tasks")
     public List<TaskRDTO> getTasksByUser() throws UserNotFoundException {
          Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          return resourceManager.getAllTasksByUsername(principal.toString());
     }

     @GetMapping(path = "/tasks/priority/{priority}")
     public List<TaskRDTO> getTasksByUserAndPriority(@PathVariable(name = "priority") String priority) throws UserNotFoundException {
          Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          return resourceManager.getAllTasksByUsernameAndPriority(principal.toString(), EnumTaskPriority.valueOf(priority.toUpperCase()));
     }

     @PostMapping(path = "/tasks")
     public List<TaskRDTO> insertTaskByUser(@RequestBody @Valid TaskDTO taskDTO) throws UserNotFoundException {
          Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          return resourceManager.insertTaskByUsername(principal.toString(), taskDTO);
     }

     @DeleteMapping(path = "/tasks")
     public void deleteTasksByUser() throws UserNotFoundException {
          Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          resourceManager.deleteAllTasksByUsername(principal.toString());
     }

     @GetMapping(path = "/tasks/title_starting/{starts}")
     public List<TaskRDTO> getTasksByUserAndTitleStartingWith(@PathVariable(name = "starts") String startsWith) throws UserNotFoundException {
          Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          return resourceManager.getAllTasksByUsernameAndTitleStartingWith(principal.toString(), startsWith);
     }


}
