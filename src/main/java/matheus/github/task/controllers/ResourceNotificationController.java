package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.messagedto.MessageDTO;
import matheus.github.task.entities.NotificationEntity;
import matheus.github.task.exception.exceptions.NullNotificationException;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.implementation.notification.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(PathConstants.DEFAULT_TASKS_RESOURCE)
public class ResourceNotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;

    @GetMapping(path = "/notification/{taskid}")
    public NotificationEntity getNotificationByTaskId(@PathVariable UUID taskid) throws TaskNotFoundException, NullNotificationException {
	   return notificationService.getNotificationByTaskId(taskid);
    }

    @PostMapping(path = "/notification/{taskid}")
    public NotificationEntity insertMessageInNotificationByTaskId(
		  @PathVariable UUID taskid,
		  @RequestBody @Valid MessageDTO messageDTO ) throws TaskNotFoundException, NullNotificationException {
	   return notificationService.addMessageByTaskId(messageDTO, taskid);
    }

}
