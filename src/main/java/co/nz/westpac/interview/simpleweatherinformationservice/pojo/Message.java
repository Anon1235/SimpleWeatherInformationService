package co.nz.westpac.interview.simpleweatherinformationservice.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 @author: matthew.yiqing.zhu
 @date:  May 1st 2024
 @description: Massage object used for formalized return as JSON format value when exception or error occured
 */
public class Message {

    public Message(){

    }

    public Message(String messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    @Setter
    @Getter
    String messageType;

    @Setter
    @Getter
    String message;

}
