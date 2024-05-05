package co.nz.westpac.interview.simpleweatherinformationservice.logsystem.aspect;

import co.nz.westpac.interview.simpleweatherinformationservice.logsystem.wrappers.BodyReaderHttpServletRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Aspect
@Component
public class LoggerAdvice {
    protected static final Logger logger = LogManager.getLogger();

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

    @AfterReturning(pointcut = "execution" +
            "(* co.nz.westpac.interview.simpleweatherinformationservice.controller.WeatherInformationController.*" +
            "(..,jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse))&&" +
            "args(..,request,response)",returning = "result")
    public void afterReturnAdvice(HttpServletRequest request, HttpServletResponse response,Object result) {
        try {
            ResponseEntity responseEntity = (ResponseEntity)result;
            ObjectMapper mapper = new ObjectMapper();
            logger.info("Response Logger: Request with ID "+request.getRequestId() + " from "+request.getRemoteAddr()+" \n\r accessed "+
                    request.getRequestURI()+ " with RequestBody: \\n\\r" +
                    new BodyReaderHttpServletRequestWrapper(request).getBodyStr() +"\\n\\r" +
                    " with returned response:  \\n\\r" +
                    mapper.writeValueAsString(responseEntity.getBody()));
        } catch (Exception e) {
            logger.error("Exception occurred in Logger advice (afterReturnAdvice)");
            e.printStackTrace();
        }
    }
}
