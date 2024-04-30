package co.nz.westpac.interview.simpleweatherinformationservice.dao;

import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;

import java.util.List;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Database access interface of weather information querying
 */
public interface WeatherRecordDao {
    public List<WeatherRecord> queryWeatherByCities(List<City> cityList);
}
