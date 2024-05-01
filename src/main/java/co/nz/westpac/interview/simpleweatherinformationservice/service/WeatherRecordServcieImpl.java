package co.nz.westpac.interview.simpleweatherinformationservice.service;

import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.DataQueryException;
import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.ServiceException;
import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.dao.WeatherRecordDao;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Service implementation of weather information querying
 */
@Service
public class WeatherRecordServcieImpl implements WeatherInformationService{

    SimpleDateFormat dataformat = new SimpleDateFormat(Constants.DATE_FORMAT);

    @Autowired
    WeatherRecordDao weatherRecordDao;

    /**
     @author: matthew.yiqing.zhu
     @date:  April 30th 2024
     @description: Return weatherRecord from DAO level and add current date information for legal datas
     @param java.util.List<co.nz.westpac.interview.simpleweatherinformationservice.pojo.City>  List of City which input from frontend
     @return java.util.List<co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord> Weather record per inputted cities
     */
    public List<WeatherRecord> queryWeatherByCities(List<City> cityList) throws ServiceException, DataQueryException, Exception {
        List<WeatherRecord> daoResultlist  = weatherRecordDao.queryWeatherByCities(cityList);
        for(WeatherRecord weatherRecord:daoResultlist){
            if(weatherRecord.getDate() == null) {
                weatherRecord.setDate(dataformat.format(new Date()));
            }
        }
        return daoResultlist;
    }
    /**
     @author: matthew.yiqing.zhu
     @date:  May 1st 2024
     @description: Call weatherRecordDao and return available cities' names for weather information querying
     @param java.util.List<co.nz.westpac.interview.simpleweatherinformationservice.pojo.City>  List of City which input from frontend
     @return java.util.List<co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord> Weather record per inputted cities
     */
    public Set<String> getAvailableCities() throws DataQueryException, Exception{
        return weatherRecordDao.getAvailableCities();
    }
}

