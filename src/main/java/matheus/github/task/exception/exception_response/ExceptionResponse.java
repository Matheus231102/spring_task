package matheus.github.task.exception.exception_response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ExceptionResponse {
     private LocalDateTime timestamp;
     private Integer status;
     private String error;
     private String message;
     private List<String> errors;
}
