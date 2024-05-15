package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.implementation.ResourceManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PathConstants.DEFAULT_TASKS_RESOURCE)
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
     public ResponseEntity<List<TaskRDTO>> getTasksByUser() throws UserNotFoundException {
          List<TaskRDTO> taskRDTOList = resourceManager.getAllTasksByUsername(getAuthenticatedUsername());
          return ResponseEntity.ok(taskRDTOList);
     }

     @GetMapping(path = "/tasks/priority/{priority}")
     public List<TaskRDTO> getTasksByUserAndPriority(@PathVariable(name = "priority") EnumTaskPriority priority) throws UserNotFoundException {
          return resourceManager.getAllTasksByUsernameAndPriority(getAuthenticatedUsername(), priority);
     }

     @GetMapping(path = "/tasks/title_starting/{starts}")
     public List<TaskRDTO> getTasksByUserAndTitleStartingWith(@PathVariable(name = "starts") String startsWith) throws UserNotFoundException {
          return resourceManager.getAllTasksByUsernameAndTitleStartingWith(getAuthenticatedUsername() , startsWith);
     }

     @GetMapping(path = "/users")
     public UserRDTO getUserInfoByUser() throws UserNotFoundException {
          return resourceManager.getUserByUsername(getAuthenticatedUsername());
     }

     @PostMapping(path = "/task")
     public List<TaskRDTO> insertTaskByUser(@RequestBody @Valid TaskDTO taskDTO) throws UserNotFoundException {
          return resourceManager.insertTaskByUsername(getAuthenticatedUsername(), taskDTO);
     }

     @PostMapping(path = "/tasks")
     public ResponseEntity<List<TaskRDTO>> insertTasksByUser(@RequestBody @Valid List<TaskDTO> taskDTOList) throws UserNotFoundException {
          List<TaskRDTO> taskRDTOList = resourceManager.insertTasksByUsername(getAuthenticatedUsername(), taskDTOList);
          return ResponseEntity.
                  status(HttpStatus.CREATED)
                  .body(taskRDTOList);
     }

     @DeleteMapping(path = "/tasks")
     public ResponseEntity deleteTasksByUser() throws UserNotFoundException {
          resourceManager.deleteAllTasksByUsername(getAuthenticatedUsername());
          return ResponseEntity.noContent().build();
     }

     @DeleteMapping(path = "/task/{taskId}")
     public ResponseEntity deleteTaskByUserAndTaskId(@PathVariable(name = "taskId") UUID taskId)
             throws UserNotFoundException {
          resourceManager.deleteTaskByUsernameAndTaskId(getAuthenticatedUsername(), taskId);
          return ResponseEntity.noContent().build();
     }

}
