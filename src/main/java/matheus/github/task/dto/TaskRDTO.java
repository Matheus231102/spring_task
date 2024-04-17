package matheus.github.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.enums.EnumTaskStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRDTO {
     private Long id;
     private String title;
     private String description;
     private EnumTaskStatus status;
     private EnumTaskPriority priority;
     private LocalDateTime creationDate;
     private UserRDTO user;
}
