package matheus.github.task.exception.exceptions.data_conflict_exception;

public class UsernameAlreadyExistsException extends Exception {
     public UsernameAlreadyExistsException() {
          super();
     }

     public UsernameAlreadyExistsException(String message) {
          super(message);
     }
}
