package co.nz.westpac.interview.simpleweatherinformationservice.pojo;

import lombok.Getter;
import lombok.Setter;

;

/**
@author: matthew.yiqing.zhu
@date:  April 30th 2024
@description: City Name Used for input query
*/
public class City {
    @Setter
    @Getter
    String cityname;

    public City(){

    }
    public City(String cityName){
        this.cityname = cityName;
    }
}
