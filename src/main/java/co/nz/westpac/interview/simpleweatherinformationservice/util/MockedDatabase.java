package co.nz.westpac.interview.simpleweatherinformationservice.util;

import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Mocked Database class for storage the weather information
 */
public class MockedDatabase {
    /**
     *  Using hash Map as mocked Database, which allow query weather by city name
     */
    private static Map<String, WeatherRecord> mockedDatebase  = new HashMap<String, WeatherRecord>();
    /**
     *  Storing cityList here, prepare for create a service to notice user available cities
     */
    private static List<String> cityList  = new ArrayList<String>();
    public static WeatherRecord getWeatherByCity(City city){
        return mockedDatebase.get(city.cityName);
    }
    /**
     *  Data initialization method, called at program starting
     */
    public static void initDatabase(){
        cityList.add(Constants.CITY_NAME_AUCKLAND);
        cityList.add(Constants.CITY_NAME_WELLINGTON);
        cityList.add(Constants.CITY_NAME_HAMILTON);
        cityList.add(Constants.CITY_NAME_TAURANGA);

        mockedDatebase.put(Constants.CITY_NAME_AUCKLAND, new WeatherRecord(Constants.CITY_NAME_AUCKLAND,getRandomTemprature(),Constants.TEMPERATURE_UNIT_C,Constants.WEATHER_CONDITION_SUNNY);
        mockedDatebase.put(Constants.CITY_NAME_WELLINGTON, new WeatherRecord(Constants.CITY_NAME_WELLINGTON,getRandomTemprature(),Constants.TEMPERATURE_UNIT_C,Constants.WEATHER_CONDITION_SNOW));
        mockedDatebase.put(Constants.CITY_NAME_HAMILTON, new WeatherRecord(Constants.CITY_NAME_HAMILTON,getRandomTemprature(),Constants.TEMPERATURE_UNIT_C,Constants.WEATHER_CONDITION_CLOUDY);
        mockedDatebase.put(Constants.CITY_NAME_TAURANGA, new WeatherRecord(Constants.CITY_NAME_TAURANGA,getRandomTemprature(),Constants.TEMPERATURE_UNIT_C,Constants.WEATHER_CONDITION_CLOUDY);
    }

    /**
     *  Return a random temperature
     */
    public static String getRandomTemprature(){
        return String.valueOf(-5+Math.random()*(30-1+1));
    }

}
