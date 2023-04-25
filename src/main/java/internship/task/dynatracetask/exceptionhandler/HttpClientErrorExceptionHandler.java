package internship.task.dynatracetask.exceptionhandler;

import org.springframework.http.HttpStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpClientErrorExceptionHandler implements ExceptionHandler{

    @Override
    public  void sendErrorInformation(HttpStatus httpStatus, Logger logger) {
        switch (httpStatus) {
            case NOT_FOUND -> logger.log(Level.WARNING, "Required resource not found " + httpStatus);
            case BAD_REQUEST -> logger.log(Level.WARNING, "Invalid request " + httpStatus);
            default -> logger.log(Level.WARNING, "Error occurred during request " + httpStatus);
        }
    }
}
