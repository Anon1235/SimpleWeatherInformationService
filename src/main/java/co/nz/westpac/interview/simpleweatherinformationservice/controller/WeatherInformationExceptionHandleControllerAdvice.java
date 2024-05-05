package co.nz.westpac.interview.simpleweatherinformationservice.controller;

import co.nz.westpac.interview.simpleweatherinformationservice.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;


/**
 @author: matthew.yiqing.zhu
 @date:  May 5th 2024
 @description: A controller advice class to provide customized controller level exception handle and make return information more  friendly
 */
@ControllerAdvice(assignableTypes = WeatherInformationController.class)
public class WeatherInformationExceptionHandleControllerAdvice {

    /**
     @author: matthew.yiqing.zhu
     @date:  May 5th 2024
     @description: For customized 400 bad request exception
     @return org.springframework.http.ResponseEntity contain the customized error message and the HTTP status information
     */
    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handelHttpMessageNotReadableException() {
            return new ResponseEntity<Object>(MessageUtil.getExceptionUnreadableMessage(), HttpStatus.BAD_REQUEST);
    }
}