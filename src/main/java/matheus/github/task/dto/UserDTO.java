package matheus.github.task.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

     @NotNull(message = "name must not be null")
     @NotEmpty(message = "name must not be empty")
     private String name;

     @NotNull(message = "username must not be null")
     @NotEmpty(message = "username must not be empty")
     private String username;

     @NotNull(message = "email must not be null")
     @NotEmpty(message = "e-mail must not be empty")
     @Email(message = "the e-mail field must be valid")
     private String email;

     @NotNull(message = "password must not be null")
     @NotEmpty(message = "password must not be empty")
     private String password;
}
