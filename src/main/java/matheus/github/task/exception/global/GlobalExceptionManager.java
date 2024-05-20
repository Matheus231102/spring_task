package matheus.github.task.exception.global;

import com.auth0.jwt.exceptions.TokenExpiredException;
import matheus.github.task.exception.ExceptionResponse;
import matheus.github.task.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionManager {
     private final String BAD_DATETIME = "bad datetime sent";
     private final String BAD_CREDENTIALS = "Bad credentials";
     private final String NO_HANDLER_FOUND = "No handler found";
     private final String FIELD_NOT_VALID = "A field sent was wrong";
     private final String EMAIL_ALREADY_EXISTS = "E-mail already exists";
     private final String USERNAME_ALREADY_EXISTS = "Username already exists";
     private final String TASK_NOT_FOUND = "Task not found";
     private final String USER_NOT_FOUND = "User not found";
     private final String EXPIRED_TOKEN = "Expired token";


     @ExceptionHandler(UserNotFoundException.class)
     public ResponseEntity handleUserNotFoundException(UserNotFoundException exception) {

          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.NOT_FOUND.value())
                  .error(USER_NOT_FOUND)
                  .message(exception.getMessage())
                  .build();
          return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler(TaskNotFoundException.class)
     public ResponseEntity handleTaskNotFoundException(TaskNotFoundException exception) {

          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.NOT_FOUND.value())
                  .error(TASK_NOT_FOUND)
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
                  .error(FIELD_NOT_VALID)
                  .message(collectedErrors)
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(MethodArgumentTypeMismatchException.class)
     public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.BAD_REQUEST.value())
                  .error(exception.getErrorCode())
                  .message(exception.getMessage())
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(NoHandlerFoundException.class)
     public ResponseEntity handleNoHandlerFoundException(NoHandlerFoundException exception) {
          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.BAD_REQUEST.value())
                  .error(NO_HANDLER_FOUND)
                  .message(exception.getMessage())
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(TokenExpiredException.class)
     public ResponseEntity handleTokenExpiredException(TokenExpiredException exception) {
          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.BAD_REQUEST.value())
                  .error(EXPIRED_TOKEN + exception.getExpiredOn())
                  .message(exception.getMessage())
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(BadCredentialsException.class)
     public ResponseEntity handleBadCredentialsException(BadCredentialsException exception) {
          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.UNAUTHORIZED.value())
                  .error(BAD_CREDENTIALS)
                  .message("Invalid password")
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.UNAUTHORIZED);
     }

     @ExceptionHandler(DateTimeParseException.class)
     public ResponseEntity handleDateTimeParseException(DateTimeParseException exception) {
          ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                  .timestamp(LocalDateTime.now())
                  .status(HttpStatus.BAD_REQUEST.value())
                  .error(BAD_DATETIME)
                  .message(exception.getMessage())
                  .build();

          return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
     }

}
