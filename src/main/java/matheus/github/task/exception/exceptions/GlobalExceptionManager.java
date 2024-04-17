package matheus.github.task.exception.exceptions;

import matheus.github.task.exception.exceptions.exception_response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionManager {
     private final String INVALID_TASK_ERROR = "Invalid Task";
     private final String INVALID_USER_ERROR = "Invalid User";
     private final String TASK_NOT_FOUND_ERROR = "Task not found";
     private final String USER_NOT_FOUND_ERROR = "User not found";

     @ExceptionHandler(UserNotFoundException.class)
     public ResponseEntity handleUserNotFoundException(UserNotFoundException exception) {

          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.NOT_FOUND.value())
                  .error(USER_NOT_FOUND_ERROR)
                  .message(exception.getMessage())
                  .build();
          return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler(TaskNotFoundException.class)
     public ResponseEntity handleTaskNotFoundException(TaskNotFoundException exception) {

          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.NOT_FOUND.value())
                  .error(TASK_NOT_FOUND_ERROR)
                  .message(exception.getMessage())
                  .build();
          return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler(InvalidUserException.class)
     public ResponseEntity handleInvalidUserException(InvalidUserException exception) {

          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.BAD_REQUEST.value())
                  .error(INVALID_USER_ERROR)
                  .message(exception.getMessage())
                  .build();
          return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(InvalidTaskException.class)
     public ResponseEntity handleInvalidTaskException(InvalidTaskException exception) {

          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.BAD_REQUEST.value())
                  .error(INVALID_TASK_ERROR)
                  .message(exception.getMessage())
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
     }



}
