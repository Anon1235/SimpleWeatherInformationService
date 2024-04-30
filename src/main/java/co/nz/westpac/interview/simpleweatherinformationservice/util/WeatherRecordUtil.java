package co.nz.westpac.interview.simpleweatherinformationservice.util;

import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;

public class WeatherRecordUtil {
    public static WeatherRecord createWeatherRecordForNotExistCity(String cityname){
        return new WeatherRecord(
                cityname + Constants.TIPS_INFORMATION_FOR_NOT_AVAILABLE_CITY,//tips information
                "n/a", //temperature
                "n/a", //unit
                "n/a", //date
                "n/a" //weather condition
            );
    }

}
