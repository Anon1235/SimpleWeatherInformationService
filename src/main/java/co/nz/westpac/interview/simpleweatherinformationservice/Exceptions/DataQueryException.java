package co.nz.westpac.interview.simpleweatherinformationservice.Exceptions;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Customized Exception for exception occurred in dao layer
 */
public class DataQueryException extends Exception{
    public DataQueryException() {super();}

    public DataQueryException(String message) {
        super(message);
    }

    public DataQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataQueryException(Throwable cause) {
        super(cause);
    }
}
