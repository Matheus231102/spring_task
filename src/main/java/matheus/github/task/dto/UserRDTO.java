package matheus.github.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRDTO {
     private Long id;
     private String name;
     private String username;
     private String email;
     private LocalDateTime creationDate;
}
