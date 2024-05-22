package matheus.github.task.dto.userdto;

import lombok.*;
import matheus.github.task.entities.TaskEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRDTO {
     private UUID id;
     private String name;
     private String username;
     private String email;
     private LocalDateTime creationDate;
     private List<TaskEntity> tasks;
}
