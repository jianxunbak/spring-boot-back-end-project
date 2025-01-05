package group3.group3_assignment.exception;

public class UserNotAuthorizeException extends RuntimeException {
    public UserNotAuthorizeException(Long userId, String action, String item) {
        super("User with id: " + userId + " is not authorize to " + action + " " + item);
    }
}
