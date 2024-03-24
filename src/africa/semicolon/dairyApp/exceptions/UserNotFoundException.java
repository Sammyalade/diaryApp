package africa.semicolon.dairyApp.exceptions;

public class UserNotFoundException extends DiaryAppException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
