package co.nz.westpac.interview.simpleweatherinformationservice.controller;

import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.DataQueryException;
import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.ServiceException;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;
import co.nz.westpac.interview.simpleweatherinformationservice.service.WeatherInformationService;
import co.nz.westpac.interview.simpleweatherinformationservice.util.MessageUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Controller class to provide the restful webservice for weather information
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WeatherInformationController {

    @Autowired
    WeatherInformationService weatherInformationService;
    /**
     @author: matthew.yiqing.zhu
     @date:  April 30th 2024
     @description: webservice endpoint method to provide the restful result for weather information
     @param java.util.List<co.nz.westpac.interview.simpleweatherinformationservice.pojo.City>  List of City which input from frontend
     @param jakarta.servlet.http.HttpServletRequest For Log System support
     @param jakarta.servlet.http.HttpServletResponse Handle http parameter which need work for the response
     @return org.springframework.http.ResponseEntity contain the query result and the HTTP status information
     */
    @GetMapping("/queryweatherbycities")
    public ResponseEntity<Object> queryWeatherInformationByCities(@RequestBody List<City> cityList, HttpServletRequest request, HttpServletResponse response) {
        //if queried cities amount > 3 return error directly
        if(cityList.size() > 3){
            return new ResponseEntity<Object>(MessageUtil.getInputExceedMessage(), HttpStatus.BAD_REQUEST);
        }
        //if no city information > 3 return error directly
        if(cityList.size() == 0){
            return new ResponseEntity<Object>(MessageUtil.getNoInputCityMessage(), HttpStatus.BAD_REQUEST);
        }
        //if input cities name has duplicated value,  return error directly
        //if some case,  input properties or name maybe null, The ckeck or put here to reduce the loop ammount
        Set<String> cityNameSet = new HashSet<String>();
        for(City cityName:cityList){
            String cityname  = cityName.getCityname();
            if (StringUtils.isBlank(cityname)){
                return new ResponseEntity<Object>(MessageUtil.getExceptionUnreadableMessage(), HttpStatus.BAD_REQUEST);
            }
            cityNameSet.add(cityname);
        }
        if(cityNameSet.size()<cityList.size()){
            return new ResponseEntity<Object>(MessageUtil.getSameCityQueryMessage(), HttpStatus.BAD_REQUEST);
        }
        //Call service to get weather information
        List<WeatherRecord> weatherRecords = new ArrayList<WeatherRecord>();
        try {
            weatherRecords = weatherInformationService.queryWeatherByCities(cityList);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(MessageUtil.getServiceExcetionMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (DataQueryException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(MessageUtil.getDaoExcetionMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(MessageUtil.getUnknowExceptionMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //For allow clients from other IP address can receive the result
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(weatherRecords, HttpStatus.OK);
    }
    /**
     @author: matthew.yiqing.zhu
     @date:  April 30th 2024
     @description: webservice endpoint method provide available cities for weather information querying
     @param jakarta.servlet.http.HttpServletRequest For Log System support
     @param jakarta.servlet.http.HttpServletResponse Handle http parameter which need work for the response
     @return org.springframework.http.ResponseEntity contain the query result and the HTTP status information
     */
    @GetMapping("/availablecities")
    public ResponseEntity<Object> getAvailableCities(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Set<String> availableCities = new HashSet<>();
        try {
            availableCities =  weatherInformationService.getAvailableCities();
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(MessageUtil.getServiceExcetionMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (DataQueryException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(MessageUtil.getDaoExcetionMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(MessageUtil.getUnknowExceptionMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(availableCities, HttpStatus.OK);
    }
    /**
     @author: matthew.yiqing.zhu
     @date:  April 30th 2024
     @description: webservice endpoint method provide manual for clients
     @param jakarta.servlet.http.HttpServletRequest For Log System support
     @param jakarta.servlet.http.HttpServletResponse Handle http parameter which need work for the response
     @return ResponseEntity contain the query result and the HTTP status information
     */
    @GetMapping("/")
    public ResponseEntity<Object> getAvailableService(HttpServletRequest request,HttpServletResponse response) {
        List<String> tips = new ArrayList<String>();
        tips.add("Welcome, currently following services are available");
        tips.add("1. /queryweatherbycities, input city list (up to 3, each city should have different name) and get current weather record");
        tips.add(" Need webservice client support and The input format like following:");
        tips.add("   [ {\"cityname\": \"Auckland\"} , {\"cityname\": \"Wellington\"}  ]   ");
        tips.add("2. /availablecities, query record of which cities are available");
        //For allow clients from other IP address can receive the result
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(tips, HttpStatus.OK);
    }
}
