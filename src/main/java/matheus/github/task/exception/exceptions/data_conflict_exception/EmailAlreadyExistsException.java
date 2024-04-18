package matheus.github.task.exception.exceptions.data_conflict_exception;

public class EmailAlreadyExistsException extends Exception {
     public EmailAlreadyExistsException() {
          super();
     }

     public EmailAlreadyExistsException(String message) {
          super(message);
     }
}
