package matheus.github.task.dto.taskdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matheus.github.task.entities.NotificationEntity;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.enums.EnumTaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRDTO {
     private UUID id;
     private String title;
     private String description;
     private EnumTaskStatus status;
     private EnumTaskPriority priority;
     private LocalDateTime creation;
     private LocalDateTime lastUpdate;
     private LocalDateTime conclusion;
     private NotificationEntity taskNotification;
}
