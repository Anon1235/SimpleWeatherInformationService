package co.nz.westpac.interview.simpleweatherinformationservice.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Data struct for mocked weather report
 */
public class WeatherRecord {

    //Empty Constructor
    public WeatherRecord(){

    }

    //Constructor with initial data
    //Did not include date because in service always return the current data
    public WeatherRecord(String city, String temp,String unit,String weather){
        this.city = city;
        this.temp = temp;
        this.unit = unit;
        this.weather = weather;
    }
    public WeatherRecord(String not_exist_cityname){
        this.city = not_exist_cityname + "not exist";
        this.temp = "n/a";
        this.unit = "n/a";
        this.weather = "n/a";
    }

    //City name
    @Setter
    @Getter
    String city;

    //City Temperature
    @Setter
    @Getter
    String temp;

    //Temperature Unit
    @Setter
    @Getter
    String unit;

    //Weather Date
    @Setter
    @Getter
    String date;

    //Weather condition
    @Setter
    @Getter
    String weather;


}
