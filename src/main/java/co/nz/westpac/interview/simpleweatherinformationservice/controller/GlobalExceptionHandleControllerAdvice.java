package co.nz.westpac.interview.simpleweatherinformationservice.controller;

import co.nz.westpac.interview.simpleweatherinformationservice.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 @author: matthew.yiqing.zhu
 @date:  May 5th 2024
 @description: A controller advice class to provide customized system level exception handle and make return information more friendly
 */
@ControllerAdvice
public class GlobalExceptionHandleControllerAdvice {
    /**
     @author: matthew.yiqing.zhu
     @date:  May 5th 2024
     @description: For customized 404 not found exception
     @return org.springframework.http.ResponseEntity contain the customized error message and the HTTP status information
     */
    @ResponseBody
    @ExceptionHandler(value =  NoResourceFoundException.class)
    public ResponseEntity<Object> handelNotFoundException() {
        return new ResponseEntity<Object>(MessageUtil.getExceptionNotFoundMessage(), HttpStatus.NOT_FOUND);
    }
    /**
     @author: matthew.yiqing.zhu
     @date:  May 5th 2024
     @description: For customized 400 bad request exception
     @return org.springframework.http.ResponseEntity contain the customized error message and the HTTP status information
     */
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException() {
        return new ResponseEntity<Object>(MessageUtil.getExceptionMethodNotAlowedMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}