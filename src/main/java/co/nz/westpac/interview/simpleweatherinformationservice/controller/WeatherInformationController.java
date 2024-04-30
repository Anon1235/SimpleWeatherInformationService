package co.nz.westpac.interview.simpleweatherinformationservice.controller;

import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;
import co.nz.westpac.interview.simpleweatherinformationservice.service.WeatherInformationService;
import co.nz.westpac.interview.simpleweatherinformationservice.util.MockedDatabase;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
     @description: webservice endpoint method  to provide the restful result for weather information
     @param java.util.List<co.nz.westpac.interview.simpleweatherinformationservice.pojo.City>  List of City which input from frontend
     @param jakarta.servlet.http.HttpServletResponse Handle http parameter which need work for the response
     @return ResponseEntity contain the query result and the HTTP status information
     */
    @RequestMapping("/queryweatherbycities")
    public ResponseEntity<Object> queryWeatherByCities(List<City> cityList, HttpServletResponse response) {
        List<WeatherRecord> weatherRecords = weatherInformationService.queryWeatherByCities(cityList);
        //For allow clients from other IP address can receive the result
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(weatherRecords, HttpStatus.OK);
    }
    /**
     @author: matthew.yiqing.zhu
     @date:  April 30th 2024
     @description: webservice endpoint method provide allowed cities
     @param jakarta.servlet.http.HttpServletResponse Handle http parameter which need work for the response
     @return ResponseEntity contain the query result and the HTTP status information
     */
    @RequestMapping("/allowedcities")
    public ResponseEntity<Object> getAllowedcities(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(MockedDatabase.getAllowedCities(), HttpStatus.OK);
    }

    //mocked test for templet test only, will remove after finished
    @RequestMapping("/test")
    public ResponseEntity<Object>mocktest(HttpServletResponse response) {
        List<City> cityList= new ArrayList<City>();
        cityList.add(new City("Auckland"));
        cityList.add(new City("Christchurch"));
        List<WeatherRecord> weatherRecords = weatherInformationService.queryWeatherByCities(cityList);
        return new ResponseEntity<Object>(weatherRecords, HttpStatus.OK);
    }
}
