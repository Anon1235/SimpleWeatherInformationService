package co.nz.westpac.interview.simpleweatherinformationservice.util;

import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;

import java.util.*;

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
    private static Set<String> citySet  = new HashSet<String>();

    /**
     *  Method to return the available Cities data
     */
    public static Set<String> getAvailableCities(){
        return citySet;
    }

    /**
     *  Mocking database querying, simple return result without business logic
     */
    public static WeatherRecord getWeatherByCity(City city){
        return mockedDatebase.get(city.getCityname());
    }
    /**
     *  Data initialization method, called at program starting
     */
    public static void initDatabase(){
        citySet.add(Constants.CITY_NAME_AUCKLAND);
        citySet.add(Constants.CITY_NAME_WELLINGTON);
        citySet.add(Constants.CITY_NAME_HAMILTON);
        citySet.add(Constants.CITY_NAME_TAURANGA);

        mockedDatebase.put(Constants.CITY_NAME_AUCKLAND, new WeatherRecord(Constants.CITY_NAME_AUCKLAND,getRandomTemperature(),Constants.TEMPERATURE_UNIT_C,null,Constants.WEATHER_CONDITION_SUNNY));
        mockedDatebase.put(Constants.CITY_NAME_WELLINGTON, new WeatherRecord(Constants.CITY_NAME_WELLINGTON,getRandomTemperature(),Constants.TEMPERATURE_UNIT_C,null,Constants.WEATHER_CONDITION_SNOW));
        mockedDatebase.put(Constants.CITY_NAME_HAMILTON, new WeatherRecord(Constants.CITY_NAME_HAMILTON,getRandomTemperature(),Constants.TEMPERATURE_UNIT_C,null,Constants.WEATHER_CONDITION_CLOUDY));
        mockedDatebase.put(Constants.CITY_NAME_TAURANGA, new WeatherRecord(Constants.CITY_NAME_TAURANGA,getRandomTemperature(),Constants.TEMPERATURE_UNIT_C,null,Constants.WEATHER_CONDITION_CLOUDY));
    }

    /**
     *  Return a random temperature
     */
    public static String getRandomTemperature(){
        return String.valueOf((int)(-5+Math.random()*(30-1+1)));
    }

}
