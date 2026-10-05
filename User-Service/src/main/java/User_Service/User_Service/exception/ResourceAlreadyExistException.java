package User_Service.User_Service.exception;

public class ResourceAlreadyExistException extends RuntimeException{

    public ResourceAlreadyExistException(String msg){
        super(msg);
    }
}
