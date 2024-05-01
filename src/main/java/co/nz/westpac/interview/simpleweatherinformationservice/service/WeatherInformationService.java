package co.nz.westpac.interview.simpleweatherinformationservice.service;

import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.DataQueryException;
import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.ServiceException;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.City;
import co.nz.westpac.interview.simpleweatherinformationservice.pojo.WeatherRecord;

import java.util.List;
import java.util.Set;

/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Service interface of weather information querying
 */
public interface WeatherInformationService {
    public List<WeatherRecord> queryWeatherByCities(List<City> cityList) throws ServiceException, DataQueryException, Exception;
    public Set<String> getAvailableCities() throws DataQueryException, Exception;
}
