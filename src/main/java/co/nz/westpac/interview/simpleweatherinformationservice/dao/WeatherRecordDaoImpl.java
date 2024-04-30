package co.nz.westpac.interview.simpleweatherinformationservice.dao;

import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;
import co.nz.westpac.interview.simpleweatherinformationservice.service.WeatherInformationService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Dao implementation of weather information querying
 */
@Repository
public class WeatherRecordDaoImpl implements WeatherRecordDao {

    public List<WeatherRecord> queryWeatherByCities(List<City> cityList){
        return null;
    }
}

