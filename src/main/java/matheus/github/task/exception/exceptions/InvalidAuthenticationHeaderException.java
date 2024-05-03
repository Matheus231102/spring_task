package matheus.github.task.exception.exceptions;

public class InvalidAuthenticationHeaderException extends Exception {
    public InvalidAuthenticationHeaderException() {
	   super();
    }

    public InvalidAuthenticationHeaderException(String message) {
	   super(message);
    }
}
