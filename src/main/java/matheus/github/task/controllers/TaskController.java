package matheus.github.task.controllers;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.exception.exceptions.InvalidTaskException;
import matheus.github.task.exception.exceptions.InvalidUserException;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.services.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/task")
public class TaskController {

     @Autowired
     private TaskServiceInterface taskService;

     @GetMapping
     public TaskRDTO getTaskById(@RequestParam(name = "taskid") Long id) throws TaskNotFoundException {
          return taskService.getTaskById(id);
     }

     @GetMapping(path = "/all")
     public List<TaskRDTO> getAllTasks() {
          return taskService.getAllTasks();
     }

     @PostMapping
     public TaskRDTO insertTask(@RequestBody TaskDTO taskDTO) throws InvalidTaskException {
          return taskService.insertTask(taskDTO);
     }

     @PostMapping(path = "/group")
     public List<TaskRDTO> insertTasks(@RequestBody List<TaskDTO> taskDTOList) throws InvalidUserException {
          return taskService.insertTasks(taskDTOList);
     }

     @DeleteMapping
     public TaskRDTO deleteTaskById(@RequestParam(name = "taskid") Long id) throws TaskNotFoundException {
          return taskService.removeTaskById(id);
     }


}
