package co.nz.westpac.interview.simpleweatherinformationservice.dao;

import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;
import co.nz.westpac.interview.simpleweatherinformationservice.service.WeatherInformationService;
import co.nz.westpac.interview.simpleweatherinformationservice.util.MockedDatabase;
import co.nz.westpac.interview.simpleweatherinformationservice.util.WeatherRecordUtil;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Dao implementation of weather information querying
 */
@Repository
public class WeatherRecordDaoImpl implements WeatherRecordDao {
    /**
     @author: matthew.yiqing.zhu
     @date:  April 30th 2024
     @description: Return weatherRecord from mocked database, and created tips information for cities not available
     @param java.util.List<co.nz.westpac.interview.simpleweatherinformationservice.pojo.City>  List of City which input from frontend
     @return java.util.List<co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord> Weather record per inputted cities
     */
    public List<WeatherRecord> queryWeatherByCities(List<City> cityList){
        List<WeatherRecord> returnList = new ArrayList<WeatherRecord>();
        for(City city:cityList){
            WeatherRecord record = MockedDatabase.getWeatherByCity(city);
            if(record != null) {
                returnList.add(record);
            }else{
                returnList.add(WeatherRecordUtil.createWeatherRecordForNotExistCity(city.getCityName()));
            }
        }
        return returnList;
    }
}

