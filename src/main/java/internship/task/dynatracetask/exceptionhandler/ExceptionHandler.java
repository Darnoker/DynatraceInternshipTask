package internship.task.dynatracetask.exceptionhandler;

import org.springframework.http.HttpStatus;

import java.util.logging.Logger;

public interface ExceptionHandler {

    void sendErrorInformation(HttpStatus status, Logger logger);
}
