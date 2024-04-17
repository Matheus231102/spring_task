package matheus.github.task.exception.exceptions;

public class UserNotFoundException extends Exception {
     public UserNotFoundException() {
          super();
     }

     public UserNotFoundException(String message) {
          super(message);
     }
}
