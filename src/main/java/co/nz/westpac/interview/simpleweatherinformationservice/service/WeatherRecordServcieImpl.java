package co.nz.westpac.interview.simpleweatherinformationservice.service;

import co.nz.westpac.interview.simpleweatherinformationservice.dao.WeatherRecordDao;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Service implementation of weather information querying
 */
@Service
public class WeatherRecordServcieImpl implements WeatherInformationService{

    @Autowired
    WeatherRecordDao weatherRecordDao;

    public List<WeatherRecord> queryWeatherByCities(List<City> cityList){
        return weatherRecordDao.queryWeatherByCities(cityList);
    }
}

