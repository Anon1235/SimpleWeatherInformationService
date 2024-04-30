package co.nz.westpac.interview.simpleweatherinformationservice.Exceptions;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Customized Exception for exception occurred in service layer
 */
public class ServiceException extends Exception{
    public ServiceException() {super();}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
