package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PathConstants.DEFAULT_TASKS_PATH)
public class TaskController {

     @Autowired
     private TaskServiceInterface taskService;
     
     @GetMapping("/{taskid}")
     public ResponseEntity<TaskRDTO> getTaskById(@PathVariable(name = "taskid") UUID id) throws TaskNotFoundException {
          TaskRDTO taskRDTO = taskService.getTaskById(id);
          return ResponseEntity
                  .status(HttpStatus.OK)
                  .body(taskRDTO);
     }

     //TODO implementar atualização de recurso
     //TODO verificar como tratar exceções de entrada de dados no Spring Boot
     // /all3 está devolvendo "message": "Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; Invalid UUID string: all2"
     @GetMapping("/all")
     public ResponseEntity<List<TaskRDTO>> getAllTasks() {
          List<TaskRDTO> taskRDTOList = taskService.getAllTasks();
          return ResponseEntity
                  .status(HttpStatus.OK)
                  .body(taskRDTOList);
     }

     @PostMapping
     public ResponseEntity<TaskRDTO> insertTask(@RequestBody @Valid TaskDTO taskDTO) {
          TaskRDTO taskRDTO = taskService.insertTask(taskDTO);
          return ResponseEntity
                  .status(HttpStatus.CREATED)
                  .body(taskRDTO);
     }

     @PostMapping("/group")
     public ResponseEntity<List<TaskRDTO>> insertTasks(@RequestBody @Valid List<TaskDTO> taskDTOList) {
          List<TaskRDTO> taskRDTOList = taskService.insertTasks(taskDTOList);
          return ResponseEntity
                  .status(HttpStatus.CREATED)
                  .body(taskRDTOList);
     }

     @DeleteMapping("/{taskid}")
     public ResponseEntity deleteTaskById(@PathVariable(name = "taskid") UUID id) throws TaskNotFoundException {
          taskService.removeTaskById(id);
          return ResponseEntity
                  .status(HttpStatus.NO_CONTENT)
                  .build();
     }

}
