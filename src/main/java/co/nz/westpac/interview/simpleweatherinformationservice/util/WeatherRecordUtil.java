package co.nz.westpac.interview.simpleweatherinformationservice.util;

import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: WeatherRecordUtil to created record for unavailable city
 */
public class WeatherRecordUtil {

    /**
     @author: matthew.yiqing.zhu
     @date:  May 1st 2024
     @description: return special WeatherRecord when input city name belong to an unavailable city
     @param java.lang.String city's name
     @return co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord WeatherRecord information with unavailable city
     */
    public static WeatherRecord createWeatherRecordForNotExistCity(String cityname){
        return new WeatherRecord(
                cityname + Constants.TIPS_INFORMATION_FOR_NOT_AVAILABLE_CITY,//tips information
                Constants.NA_VALUE, //temperature
                Constants.NA_VALUE, //unit
                Constants.NA_VALUE, //date
                Constants.NA_VALUE //weather condition
            );
    }

}
