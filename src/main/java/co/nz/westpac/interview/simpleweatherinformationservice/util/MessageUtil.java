package co.nz.westpac.interview.simpleweatherinformationservice.util;

import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message;
import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 @author: matthew.yiqing.zhu
 @date:  May 1st 2024
 @description: For the massage never changed, use unique ones to avoid to many objects are created
 */
public class MessageUtil {
    /**
     * Massage used for input cities more than 3
     */
    private static Message INPUT_EXCEED_MESSAGE;

    /**
     * Massage used for no input city information
     */
    private static Message NO_INPUT_CITY_MESSAGE;

    /**
     * Massage used for service layer exception occurred
     */
    private static Message SERVICE_EXCEPTION_MESSAGE;

    /**
     * Massage used for dao layer exception occurred
     */
    private static Message DAO_EXCEPTION_MESSAGE;

    /**
     * Massage used for unknown exception occurred
     */
    private static Message UNKNOW_EXCEPTION_MESSAGE;

    /**
     * Massage used for query contain same cities' name
     */
    private static Message SAME_CITY_QUERY_MESSAGE;

    /**
     * Massage used for 400 Bad Request Exception
     */
    private static Message EXCEPTION_MESSAGE_UNREADABLE;


    /**
     * Massage used for 404 Not Found Exception
     */
    private static Message EXCEPTION_NOT_FOUND;

    /**
     * Massage used for 405 Method Not Allowed
     */
    private static Message EXCEPTION_Method_NOT_ALLOWED;

    /**
     @author: matthew.yiqing.zhu
     @date:  May 1st 2024
     @description: Use factory pattern to return unique Massage  object for input cities more than 3 exception
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message
     */
    public static Message getInputExceedMessage(){
        if(INPUT_EXCEED_MESSAGE!=null){
            return INPUT_EXCEED_MESSAGE;
        }else{
            INPUT_EXCEED_MESSAGE = new Message(Constants.MASSAGE_TYPE_TIPS,Constants.TIPS_INPUT_EXCEED);
            return INPUT_EXCEED_MESSAGE;
        }
    }

    /**
     @author: matthew.yiqing.zhu
     @date:  May 1st 2024
     @description: Use factory pattern to return unique Massage  object for input cities more than 3 exception
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message
     */
    public static Message getNoInputCityMessage(){
        if(NO_INPUT_CITY_MESSAGE!=null){
            return NO_INPUT_CITY_MESSAGE;
        }else{
            NO_INPUT_CITY_MESSAGE = new Message(Constants.MASSAGE_TYPE_TIPS,Constants.TIPS_NO_INPUT_CITY);
            return NO_INPUT_CITY_MESSAGE;
        }
    }

    /**
     @author: matthew.yiqing.zhu
     @date: May 1st 2024
     @description: Use factory pattern to return unique Massage object for service layer exception occurred
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message
     */
    public static Message getServiceExcetionMessage(){
        if(SERVICE_EXCEPTION_MESSAGE!=null){
            return SERVICE_EXCEPTION_MESSAGE;
        }else{
            SERVICE_EXCEPTION_MESSAGE = new Message(Constants.MASSAGE_TYPE_ERROR,Constants.ERROR_SERVICE_EXCEPTION);
            return SERVICE_EXCEPTION_MESSAGE;
        }
    }

    /**
     @author: matthew.yiqing.zhu
     @date: May 1st 2024
     @description: Use factory pattern to return unique Massage object for dao layer exception occurred
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message
     */
    public static Message getDaoExcetionMessage(){
        if(DAO_EXCEPTION_MESSAGE!=null){
            return DAO_EXCEPTION_MESSAGE;
        }else{
            DAO_EXCEPTION_MESSAGE = new Message(Constants.MASSAGE_TYPE_ERROR,Constants.ERROR_DAO_EXCEPTION);
            return DAO_EXCEPTION_MESSAGE;
        }
    }


    /**
     @author: matthew.yiqing.zhu
     @date: May 1st 2024
     @description: Use factory pattern to return unique Massage object for unknown exceptions occurred
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message
     */
    public static Message getUnknowExceptionMessage(){
        if(UNKNOW_EXCEPTION_MESSAGE!=null){
            return UNKNOW_EXCEPTION_MESSAGE;
        }else{
            UNKNOW_EXCEPTION_MESSAGE = new Message(Constants.MASSAGE_TYPE_ERROR,Constants.ERROR_UNKNOW_EXCEPTION);
            return UNKNOW_EXCEPTION_MESSAGE;
        }
    }

    /**
     @author: matthew.yiqing.zhu
     @date: May 1st 2024
     @description: Use factory pattern to return unique Massage object for same city occurred
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message
     */
    public static Message getSameCityQueryMessage(){
        if(SAME_CITY_QUERY_MESSAGE!=null){
            return SAME_CITY_QUERY_MESSAGE;
        }else{
            SAME_CITY_QUERY_MESSAGE = new Message(Constants.MASSAGE_TYPE_ERROR,Constants.SAME_CITY_QUERY_MESSAGE);
            return SAME_CITY_QUERY_MESSAGE;
        }
    }

    /**
     @author: matthew.yiqing.zhu
     @date: May 5th 2024
     @description: Use factory pattern
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message
     */
    public static Message getExceptionUnreadableMessage(){
        if(EXCEPTION_MESSAGE_UNREADABLE!=null){
            return EXCEPTION_MESSAGE_UNREADABLE;
        }else{
            EXCEPTION_MESSAGE_UNREADABLE = new Message(Constants.MASSAGE_TYPE_ERROR,Constants.EXCEPTION_MESSAGE_UNREADABLE);
            return EXCEPTION_MESSAGE_UNREADABLE;
        }
    }

    /**
     @author: matthew.yiqing.zhu
     @date: May 5th 2024
     @description: Use factory pattern
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message
     */
    public static Message getExceptionNotFoundMessage(){
        if(EXCEPTION_NOT_FOUND!=null){
            return EXCEPTION_NOT_FOUND;
        }else{
            EXCEPTION_NOT_FOUND = new Message(Constants.MASSAGE_TYPE_ERROR,Constants.EXCEPTION_MESSAGE_NOT_FOUND);
            return EXCEPTION_NOT_FOUND;
        }
    }

    /**
     @author: matthew.yiqing.zhu
     @date: May 5th 2024
     @description: Use factory pattern
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message
     */
    public static Message getExceptionMethodNotAlowedMessage(){
        if(EXCEPTION_Method_NOT_ALLOWED!=null){
            return EXCEPTION_Method_NOT_ALLOWED;
        }else{
            EXCEPTION_Method_NOT_ALLOWED = new Message(Constants.MASSAGE_TYPE_ERROR,Constants.EXCEPTION_MESSAGE_NOT_ALLOWED);
            return EXCEPTION_Method_NOT_ALLOWED;
        }
    }


}
