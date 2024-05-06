package matheus.github.task.services.implementation.notification;

import matheus.github.task.dto.messagedto.MessageDTO;
import matheus.github.task.dto.taskdto.TaskRDTO;
import matheus.github.task.entities.MessageEntity;
import matheus.github.task.entities.NotificationEntity;
import matheus.github.task.exception.exceptions.NullNotificationException;
import matheus.github.task.exception.exceptions.TaskNotFoundException;
import matheus.github.task.repositories.NotificationRepository;
import matheus.github.task.services.implementation.task.TaskServiceImpl;
import matheus.github.task.services.interfaces.NotificationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationServiceInterface {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationEntity getNotificationByTaskId(UUID taskId) throws TaskNotFoundException, NullNotificationException {
	   TaskRDTO taskRDTO = taskService.getTaskById(taskId);
	   if (taskRDTO.getTaskNotification() != null) {
		  return taskRDTO.getTaskNotification();
	   }
	   throw new NullNotificationException("The task notification must not be null");
    }

    public NotificationEntity addMessageByTaskId(MessageDTO messageDTO, UUID taskId) throws TaskNotFoundException, NullNotificationException {
	   NotificationEntity notification = getNotificationByTaskId(taskId);
	   MessageEntity messageEntity = MessageEntity.create().
			 setMessage(messageDTO.getMessage());

	   notification.getMessages().add(messageEntity);
	   notificationRepository.save(notification);
	   return notification;
    }
}
