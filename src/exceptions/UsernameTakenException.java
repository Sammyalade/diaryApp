package exceptions;

public class UsernameTakenException extends DiaryAppException{
    public UsernameTakenException(String message) {
        super(message);
    }
}
