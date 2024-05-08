package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.taskdto.TaskDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PathConstants.DEFAULT_TASKS_PATH)
public class TaskController {

     @Autowired
     private TaskServiceInterface taskService;

     @GetMapping
     @ResponseStatus(HttpStatus.OK)
     public TaskRDTO getTaskById(@RequestParam(name = "taskid") UUID id) throws TaskNotFoundException {
          return taskService.getTaskById(id);
     }

     @GetMapping("/all")
     @ResponseStatus(HttpStatus.OK)
     public List<TaskRDTO> getAllTasks() {
          return taskService.getAllTasks();
     }

     @PostMapping
     @ResponseStatus(HttpStatus.OK)
     public TaskRDTO insertTask(@RequestBody @Valid TaskDTO taskDTO) {
          return taskService.insertTask(taskDTO);
     }

     @PostMapping("/group")
     @ResponseStatus(HttpStatus.CREATED)
     public List<TaskRDTO> insertTasks(@RequestBody @Valid List<TaskDTO> taskDTOList) {
          return taskService.insertTasks(taskDTOList);
     }

     @DeleteMapping
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public TaskRDTO deleteTaskById(@RequestParam(name = "taskid") UUID id) throws TaskNotFoundException {
          return taskService.removeTaskById(id);
     }

}
