package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.messagedto.MessageDTO;
import matheus.github.task.entities.NotificationEntity;
import matheus.github.task.exception.exceptions.NullNotificationException;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.services.implementation.notification.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/resource")
public class ResourceNotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;

    @GetMapping(path = "/notification/{taskId}")
    public NotificationEntity getNotificationByTaskId(@PathVariable(name = "taskId") UUID taskId) throws TaskNotFoundException, NullNotificationException {
	   return notificationService.getNotificationByTaskId(taskId);
    }

    @PostMapping(path = "/notification/{taskId}")
    public NotificationEntity isnertMessageInNotificationByTaskId(
		  @PathVariable(name = "taskId") UUID taskId,
		  @RequestBody @Valid MessageDTO messageDTO ) throws TaskNotFoundException, NullNotificationException {
	   return notificationService.addMessageByTaskId(messageDTO, taskId);
    }

}
