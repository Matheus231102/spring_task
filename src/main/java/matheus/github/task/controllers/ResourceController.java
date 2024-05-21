package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.security.AuthenticationContext;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.implementation.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PathConstants.DEFAULT_TASKS_RESOURCE)
public class ResourceController extends AuthenticationContext {

     @Autowired
     private ResourceManager resourceManager;

     @Autowired
     private AuthenticationContext authenticationContext;

     @GetMapping(path = "/tasks")
     public ResponseEntity<List<TaskRDTO>> getTasksByUser() throws UserNotFoundException {
          List<TaskRDTO> taskRDTOList = resourceManager.getAllTasksByUsername(getAuthenticatedUsername());
          return ResponseEntity
                  .ok(taskRDTOList);
     }

     @GetMapping(path = "/users")
     public ResponseEntity<UserRDTO> getUserInfoByUser() throws UserNotFoundException {
          UserRDTO userRDTO = resourceManager.getUserByUsername(getAuthenticatedUsername());
          return ResponseEntity
                  .ok()
                  .body(userRDTO);
     }

     @PostMapping(path = "/task")
     public ResponseEntity<List<TaskRDTO>> insertTaskByUser(@RequestBody @Valid TaskDTO taskDTO) throws UserNotFoundException {
          List<TaskRDTO> taskRDTOList = resourceManager.insertTaskByUsername(getAuthenticatedUsername(), taskDTO);
          return ResponseEntity
                  .ok()
                  .body(taskRDTOList);
     }

     @PostMapping(path = "/tasks")
     public ResponseEntity<List<TaskRDTO>> insertTasksByUser(@RequestBody @Valid List<TaskDTO> taskDTOList) throws UserNotFoundException {
          List<TaskRDTO> taskRDTOList = resourceManager.insertTasksByUsername(getAuthenticatedUsername(), taskDTOList);
          return ResponseEntity
                  .status(HttpStatus.CREATED)
                  .body(taskRDTOList);
     }

     @DeleteMapping(path = "/tasks")
     public ResponseEntity deleteTasksByUser() throws UserNotFoundException {
          resourceManager.deleteAllTasksByUsername(getAuthenticatedUsername());
          return ResponseEntity
                  .noContent()
                  .build();
     }

     @DeleteMapping(path = "/task/{taskId}")
     public ResponseEntity deleteTaskByUserAndTaskId(@PathVariable(name = "taskId") UUID taskId) throws UserNotFoundException {
          resourceManager.deleteTaskByUsernameAndTaskId(getAuthenticatedUsername(), taskId);
          return ResponseEntity
                  .noContent()
                  .build();
     }

}
