package matheus.github.task.services.interfaces;

import matheus.github.task.entities.NotificationEntity;
import matheus.github.task.exception.exceptions.NullNotificationException;
import matheus.github.task.exception.exceptions.TaskNotFoundException;

import java.util.UUID;

public interface NotificationServiceInterface {
    NotificationEntity getNotificationByTaskId(UUID taskId) throws TaskNotFoundException, NullNotificationException;
}
