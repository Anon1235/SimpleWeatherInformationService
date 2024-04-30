package co.nz.westpac.interview.simpleweatherinformationservice.util;

import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.Message;

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
     * Massage used for unknown exception occurred
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
     @date:  May 1st 2024
     @description: Use factory pattern to return unique Massage object for no input city information exception
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
     @description: Use factory pattern to return unique Massage object for unknow exception occurred
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

}
