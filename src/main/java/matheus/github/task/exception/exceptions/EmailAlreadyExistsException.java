package matheus.github.task.exception.exceptions;

public class EmailAlreadyExistsException extends Exception {
     public EmailAlreadyExistsException() {
          super();
     }

     public EmailAlreadyExistsException(String message) {
          super(message);
     }
}
