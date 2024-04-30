package co.nz.westpac.interview.simpleweatherinformationservice.Exceptions;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Customized Exception for exception occurred in dao layer
 */
public class DataqueryException extends Exception{
    public DataqueryException() {super();}

    public DataqueryException(String message) {
        super(message);
    }

    public DataqueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataqueryException(Throwable cause) {
        super(cause);
    }
}
