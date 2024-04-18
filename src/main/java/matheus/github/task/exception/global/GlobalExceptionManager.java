package matheus.github.task.exception.global;

import matheus.github.task.exception.exception_response.ExceptionResponse;
import matheus.github.task.exception.exceptions.*;
import matheus.github.task.exception.exceptions.data_conflict_exception.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.data_conflict_exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionManager {
     private final String FIELD_NOT_VALID_ERROR = "A field sent was wrong";
     private final String EMAIL_ALREADY_EXISTS = "E-mail already exists.";
     private final String USERNAME_ALREADY_EXISTS = "Username already exists.";
     private final String TASK_NOT_FOUND_ERROR = "Task not found.";
     private final String USER_NOT_FOUND_ERROR = "User not found.";

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

     @ExceptionHandler(EmailAlreadyExistsException.class)
     public ResponseEntity handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {

          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.CONFLICT.value())
                  .error(EMAIL_ALREADY_EXISTS)
                  .message(exception.getMessage())
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
     }

     @ExceptionHandler(UsernameAlreadyExistsException.class)
     public ResponseEntity handleUsernameAlreadyExistsException(UsernameAlreadyExistsException exception) {

          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.CONFLICT.value())
                  .error(USERNAME_ALREADY_EXISTS)
                  .message(exception.getMessage())
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

          String collectedErrors = exception.getFieldErrors()
                  .stream()
                  .map(fieldError -> fieldError.getDefaultMessage())
                  .collect(Collectors.joining("; "));

          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.BAD_REQUEST.value())
                  .error(FIELD_NOT_VALID_ERROR)
                  .message(collectedErrors)
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
     }


}
