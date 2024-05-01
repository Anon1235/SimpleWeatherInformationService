package co.nz.westpac.interview.simpleweatherinformationservice.util;

import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;

public class WeatherRecordUtil {
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
