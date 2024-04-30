package co.nz.westpac.interview.simpleweatherinformationservice.service;

import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;

import java.util.List;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Database service of weather information querying
 */
public interface WeatherInformationService {
    public List<WeatherRecord> queryWeatherByCities(List<City> cityList);
}
