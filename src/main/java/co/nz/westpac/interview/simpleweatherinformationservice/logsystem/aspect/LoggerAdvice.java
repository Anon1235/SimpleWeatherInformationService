package co.nz.westpac.interview.simpleweatherinformationservice.logsystem.aspect;

import co.nz.westpac.interview.simpleweatherinformationservice.logsystem.wrappers.BodyReaderHttpServletRequestWrapper;
import co.nz.westpac.interview.simpleweatherinformationservice.logsystem.wrappers.BodyReaderHttpServletResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 @author: matthew.yiqing.zhu
 @date:  May 3rd 2024
 @description: Use Aspect to catch the input and output of webservice and log
 */
@Aspect
@Component
public class LoggerAdvice {
    protected static final Logger logger = LogManager.getLogger();

    /**
     @author: matthew.yiqing.zhu
     @date:  May 3rd 2024
     @description: Use @Before tag to get the input of web service
     @return void
     */
    @Before("execution" +
            "(* co.nz.westpac.interview.simpleweatherinformationservice.controller.WeatherInformationController.*" +
            "(..,jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse))&&" +
            "args(..,request,response)")
    public void beforeAdvice(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("Request Logger: Request with ID "+ request.getRequestId() + " from "+request.getRemoteAddr()+" \n\r accessed "+
                    request.getRequestURI()+ " with RequestBody: \n\r"+
                    new BodyReaderHttpServletRequestWrapper(request).getBodyStr());
        } catch (IOException e) {
            logger.error("IOException occurred in Logger advice (beforeAdvice)");
            e.printStackTrace();
        }catch (Exception e) {
            logger.error("Exception occurred in Logger advice (beforeAdvice)");
            e.printStackTrace();
        }
    }

    /**
     @author: matthew.yiqing.zhu
     @date:  May 3rd 2024
     @description: Use @AfterReturning tag to get the result(output) of web service
     @return void
     */
    @AfterReturning(pointcut = "execution" +
            "(* co.nz.westpac.interview.simpleweatherinformationservice.controller.WeatherInformationController.*" +
            "(..,jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse))&&" +
            "args(..,request,response)",returning = "result")
    public void afterReturnAdvice(HttpServletRequest request, HttpServletResponse response,Object result) {
        try {
            ResponseEntity responseEntity = (ResponseEntity)result;
            ObjectMapper mapper = new ObjectMapper();
            logger.info("Response Logger: Request with ID "+request.getRequestId() + " from "+request.getRemoteAddr()+" \n\r accessed "+
                    request.getRequestURI()+ " with RequestBody: \n\r" +
                    new BodyReaderHttpServletRequestWrapper(request).getBodyStr() +"\n\r" +
                    " with returned response:  \n\r" +
                    mapper.writeValueAsString(responseEntity.getBody()));
        } catch (Exception e) {
            logger.error("Exception occurred in Logger advice (afterReturnAdvice)");
            e.printStackTrace();
        }
    }
}
