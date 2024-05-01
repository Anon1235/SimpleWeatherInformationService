package co.nz.westpac.interview.simpleweatherinformationservice.constants;
/**
 @author: matthew.yiqing.zhu
 @date:  April 30th 2024
 @description: Constants values
 */
public class Constants {
    public static String TEMPERATURE_UNIT_C = "C";

    public static String TEMPERATURE_UNIT_F = "F";

    public static String WEATHER_CONDITION_SUNNY = "sunny";

    public static String WEATHER_CONDITION_RAINY = "rainy";

    public static String WEATHER_CONDITION_CLOUDY = "cloudy";

    public static String WEATHER_CONDITION_SNOW = "snow";

    public static String WEATHER_CONDITION_STORM = "storm";

    public static String CITY_NAME_AUCKLAND = "Auckland";

    public static String CITY_NAME_WELLINGTON = "Wellington";

    public static String CITY_NAME_HAMILTON = "Hamilton";

    public static String CITY_NAME_TAURANGA = "Tauranga";

    public static String DATE_FORMAT = "dd/MM/yyyy";

    public static String NA_VALUE = "n/a";

    public static String TIPS_INFORMATION_FOR_NOT_AVAILABLE_CITY = " does not has record, please visit '/availabilities' service to get available cities list";

    public static String TIPS_INPUT_EXCEED = "Sorry, you only can query up tp 3 records in one time";

    public static String TIPS_NO_INPUT_CITY = "At least one city information needed";

    public static String MASSAGE_TYPE_ERROR = "ERROR";

    public static String MASSAGE_TYPE_TIPS = "TIPS";

    public static String MASSAGE_TYPE_WARN = "WARN";

    public static String ERROR_SERVICE_EXCEPTION = "Sorry, some exception when we try to call the service, please try late or contact service supplier";

    public static String ERROR_DAO_EXCEPTION = "Sorry, some exception when get data, please try late or contact service supplier";

    public static String ERROR_UNKNOW_EXCEPTION = "Sorry, some exception occurred, please try late or contact service supplier";
}
