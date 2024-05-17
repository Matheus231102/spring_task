package matheus.github.task.dto.taskdto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class TaskDTO {

     @NotEmpty(message = "title must not be empty")
     @NotNull(message = "title must not be null")
     private String title;

     @NotNull(message = "description must not be null")
     private String description;

     @NotNull(message = "status must not be null")
     private EnumTaskStatus status;

     @NotNull(message = "priority name must not be null")
     private EnumTaskPriority priority;

     @NotNull(message = "Conclusion date must no be null")
     private LocalDateTime conclusion;

}
