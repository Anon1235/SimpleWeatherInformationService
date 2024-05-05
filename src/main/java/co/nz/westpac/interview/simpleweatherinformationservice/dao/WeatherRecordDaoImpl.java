package co.nz.westpac.interview.simpleweatherinformationservice.dao;

import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.DataQueryException;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;
import co.nz.westpac.interview.simpleweatherinformationservice.util.MockedDatabase;
import co.nz.westpac.interview.simpleweatherinformationservice.util.WeatherRecordUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<WeatherRecord> queryWeatherByCities(List<City> cityList) throws DataQueryException, Exception{
        List<WeatherRecord> returnList = new ArrayList<WeatherRecord>();
        for(City city:cityList){
            //because this is mocked in memory database instead of query in one time
            //When use real RDS, need be optimised to access database only once)
            WeatherRecord record = MockedDatabase.getWeatherByCity(city);
            if(record != null) {
                returnList.add(record);
            }else{
                returnList.add(WeatherRecordUtil.createWeatherRecordForNotExistCity(city.getCityname()));
            }
        }
        return returnList;
    }

    /**
     @author: matthew.yiqing.zhu
     @date:  May 1st 2024
     @description: Return available cities' names for weather information querying
     @return java.util.List<co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord> Weather record per inputted cities
     */
    public Set<String> getAvailableCities() throws DataQueryException, Exception{
        return MockedDatabase.getAvailableCities();
    }
}

