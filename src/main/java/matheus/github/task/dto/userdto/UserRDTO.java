package matheus.github.task.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRDTO {
     private UUID id;
     private String name;
     private String username;
     private String email;
     private LocalDateTime creationDate;
}
