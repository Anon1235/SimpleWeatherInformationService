package co.nz.westpac.interview.simpleweatherinformationservice;


import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.util.MockedDatabase;
import co.nz.westpac.interview.simpleweatherinformationservice.util.NumberMatcher;
import co.nz.westpac.interview.simpleweatherinformationservice.util.UnitMatcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleWeatherInformationServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat(Constants.DATE_FORMAT);

	private static final NumberMatcher NUMBER_MACTHER = new NumberMatcher();

	private static final UnitMatcher UNIT_MATCHER = new UnitMatcher();

	private static final String JSON_INPUT_1_CITY = "[{\"cityname\":\"Auckland\"}]";

	private static final String JSON_INPUT_1_CITY_NOT_AVAILABLE = "[{\"cityname\":\"ChristChurch\"}]";

	private static final String JSON_INPUT_3_CITIES_ALL_AVAILABLE  = "[{\"cityname\":\"Auckland\"},{\"cityname\":\"Wellington\"},{\"cityname\":\"Hamilton\"}]";

	private static final String JSON_INPUT_3_CITY_ANT_1_CITY_NOT_AVAILABLE = "[{\"cityname\":\"Auckland\"},{\"cityname\":\"Wellington\"},{\"cityname\":\"ChristChurch\"}]";

	private static final String JSON_INPUT_NO_CITY = "[]";

	private static final String JSON_INPUT_4_CITIES =  "[{\"cityname\":\"Auckland\"},{\"cityname\":\"Wellington\"},{\"cityname\":\"Hamilton\"},{\"cityname\":\"Tauranga\"}]";;

	private static final String JSON_INPUT_CONTAIN_SAME_CITY_NAME = "[{\"cityname\":\"Auckland\"},{\"cityname\":\"Auckland\"}]";

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case for check the query result weather information, with data of 1 city
	 */
	@Test
	public void whenQueryWeatherRecordSuccessForOneCity () throws Exception{
		MockedDatabase.initDatabase();
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_1_CITY))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value("Auckland"))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].temp").value(NUMBER_MACTHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].unit").value(UNIT_MATCHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(DATA_FORMAT.format(new Date())))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].weather").isString())
						.andDo(MockMvcResultHandlers.print());
	}

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case for check the query result weather information, with date of 1 unavailable city
	 */
	@Test
	public void whenQueryWeatherRecordUnavailableForOneCity () throws Exception{
		MockedDatabase.initDatabase();
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_1_CITY_NOT_AVAILABLE))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value(Matchers.containsString(Constants.TIPS_INFORMATION_FOR_NOT_AVAILABLE_CITY)))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].temp").value(Constants.NA_VALUE))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].unit").value(Constants.NA_VALUE))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(Constants.NA_VALUE))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].weather").value(Constants.NA_VALUE))
						.andDo(MockMvcResultHandlers.print());
	}
	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case for check the query result weather information, with data of 3 cities
	 */
	@Test
	public void whenQueryWeatherRecordSuccessForThreeCities () throws Exception{
		MockedDatabase.initDatabase();
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_3_CITIES_ALL_AVAILABLE))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value("Auckland"))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].temp").value(NUMBER_MACTHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].unit").value(UNIT_MATCHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(DATA_FORMAT.format(new Date())))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].weather").isString())
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].city").value("Wellington"))
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].temp").value(NUMBER_MACTHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].unit").value(UNIT_MATCHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].date").value(DATA_FORMAT.format(new Date())))
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].weather").isString())
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].city").value("Hamilton"))
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].temp").value(NUMBER_MACTHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].unit").value(UNIT_MATCHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].date").value(DATA_FORMAT.format(new Date())))
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].weather").isString())
						.andDo(MockMvcResultHandlers.print());
	}
	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case for check the query result weather information, with data of 3 cities, 1 is unavailable
	 */
	@Test
	public void whenQueryWeatherRecordSuccessForThreeCitiesWithOneUnavailable() throws Exception{
		MockedDatabase.initDatabase();
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_3_CITY_ANT_1_CITY_NOT_AVAILABLE))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value("Auckland"))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].temp").value(NUMBER_MACTHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].unit").value(UNIT_MATCHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(DATA_FORMAT.format(new Date())))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].weather").isString())
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].city").value("Wellington"))
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].temp").value(NUMBER_MACTHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].unit").value(UNIT_MATCHER))
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].date").value(DATA_FORMAT.format(new Date())))
						.andExpect(MockMvcResultMatchers.jsonPath("$[1].weather").isString())
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].city").value(Matchers.containsString(Constants.TIPS_INFORMATION_FOR_NOT_AVAILABLE_CITY)))
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].temp").value(Constants.NA_VALUE))
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].unit").value(Constants.NA_VALUE))
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].date").value(Constants.NA_VALUE))
						.andExpect(MockMvcResultMatchers.jsonPath("$[2].weather").value(Constants.NA_VALUE))
						.andDo(MockMvcResultHandlers.print());
	}

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case for check the query result weather information, with empty input
	 */
	@Test
	public void whenQueryWeatherRecordNoCityInput () throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_NO_CITY))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_TIPS))
						.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.TIPS_NO_INPUT_CITY))
						.andDo(MockMvcResultHandlers.print());
	}
	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case for check the query result weather information, with data of 4 cities
	 */
	@Test
	public void whenQueryWeatherRecordMoreThanThreeCitiesInput () throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_4_CITIES))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_TIPS))
						.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.TIPS_INPUT_EXCEED))
						.andDo(MockMvcResultHandlers.print());
	}
	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case for check the query result weather information, with data of 2 cities has same name
	 */
	@Test
	public void whenQueryWeatherRecordSameCityInformationInput () throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_CONTAIN_SAME_CITY_NAME))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.SAME_CITY_QUERY_MESSAGE))
				.andDo(MockMvcResultHandlers.print());
	}
}
