package by.task.kukjan.exception;

public class ThreadException extends Exception{
    public ThreadException(){
        super();
    }

    public ThreadException(String message){
        super(message);
    }

    public ThreadException(Exception e) {
        super(e);
    }

    public ThreadException(String message, Exception e) {
        super(message, e);
    }
}
