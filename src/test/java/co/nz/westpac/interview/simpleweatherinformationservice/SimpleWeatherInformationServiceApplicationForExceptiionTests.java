package co.nz.westpac.interview.simpleweatherinformationservice;

import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.DataQueryException;
import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.ServiceException;
import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.dao.WeatherRecordDao;
import co.nz.westpac.interview.simpleweatherinformationservice.service.WeatherInformationService;
import co.nz.westpac.interview.simpleweatherinformationservice.util.MockedDatabase;
import co.nz.westpac.interview.simpleweatherinformationservice.util.NumberMatcher;
import co.nz.westpac.interview.simpleweatherinformationservice.util.UnitMatcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleWeatherInformationServiceApplicationForExceptiionTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherInformationService weatherInformationService;

	@Autowired
	ApplicationContext context;
	private static final String JSON_INPUT_1_CITY = "[{\"cityname\":\"Auckland\"}]";

	@Test
	public void whenQueryWeatherRecordGetDaoException () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherInformationService.queryWeatherByCities(anyList())).thenThrow(new DataQueryException());
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_1_CITY))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
						.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ERROR_DAO_EXCEPTION))
						.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void whenQueryWeatherRecordGetServiceException () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherInformationService.queryWeatherByCities(anyList())).thenThrow(new ServiceException());
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_1_CITY))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ERROR_SERVICE_EXCEPTION))
				.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void whenQueryWeatherRecordGetUnknowException () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherInformationService.queryWeatherByCities(anyList())).thenThrow(new Exception());
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_1_CITY))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ERROR_UNKNOW_EXCEPTION))
				.andDo(MockMvcResultHandlers.print());
	}
}
