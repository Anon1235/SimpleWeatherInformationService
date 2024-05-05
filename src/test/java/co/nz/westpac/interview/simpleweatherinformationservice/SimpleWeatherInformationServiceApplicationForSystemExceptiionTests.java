package co.nz.westpac.interview.simpleweatherinformationservice;

import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.DataQueryException;
import co.nz.westpac.interview.simpleweatherinformationservice.Exceptions.ServiceException;
import co.nz.westpac.interview.simpleweatherinformationservice.constants.Constants;
import co.nz.westpac.interview.simpleweatherinformationservice.dao.WeatherRecordDao;
import co.nz.westpac.interview.simpleweatherinformationservice.service.WeatherInformationService;
import co.nz.westpac.interview.simpleweatherinformationservice.service.WeatherRecordServcieImpl;
import co.nz.westpac.interview.simpleweatherinformationservice.util.MockedDatabase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleWeatherInformationServiceApplicationForSystemExceptionTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherInformationService weatherInformationService;

	@InjectMocks
	private WeatherInformationService weatherInformationServiceForServiceTest = new WeatherRecordServcieImpl();

	@Mock
	private WeatherRecordDao weatherRecordDaoForServiceTest;;

	@Autowired
	ApplicationContext context;
	private static final String JSON_INPUT_1_CITY = "[{\"cityname\":\"Auckland\"}]";

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and WeatherInformationController,
	 make sure DataQueryException can be thrown to WeatherInformationController from weatherInformationService
	 when call queryWeatherByCities method
	 */
	@Test
	public void whenQueryWeatherInformationControllerGotDaoExceptionFromService() throws Exception{
		MockedDatabase.initDatabase();
		when(weatherInformationService.queryWeatherByCities(anyList())).thenThrow(new DataQueryException());
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_1_CITY))
						.andExpect(MockMvcResultMatchers.status().isInternalServerError())
						.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
						.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ERROR_DAO_EXCEPTION))
						.andDo(MockMvcResultHandlers.print());
	}

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and WeatherInformationController,
	 make sure ServiceException can be thrown to WeatherInformationController from weatherInformationService
	 when call queryWeatherByCities method
	 */
	@Test
	public void whenQueryWeatherInformationControllerGotServiceExceptionFromService () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherInformationService.queryWeatherByCities(anyList())).thenThrow(new ServiceException());
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_1_CITY))
						.andExpect(MockMvcResultMatchers.status().isInternalServerError())
						.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
						.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ERROR_SERVICE_EXCEPTION))
						.andDo(MockMvcResultHandlers.print());
	}
	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and WeatherInformationController,
	 make sure unknown exceptions can be thrown to WeatherInformationController from weatherInformationService
	 when call queryWeatherByCities method
	 */
	@Test
	public void whenQueryWeatherInformationControllerGotUnknownExceptionFromService () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherInformationService.queryWeatherByCities(anyList())).thenThrow(new Exception());
		mockMvc.perform(MockMvcRequestBuilders.get("/queryweatherbycities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSON_INPUT_1_CITY))
						.andExpect(MockMvcResultMatchers.status().isInternalServerError())
						.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
						.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ERROR_UNKNOW_EXCEPTION))
						.andDo(MockMvcResultHandlers.print());
	}

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and WeatherInformationController,
	 make sure ServiceException can be thrown to WeatherInformationController from weatherInformationService
	 when call getAvailableCities method
	 */
	@Test
	public void whenQueryAvailableCitiesControllerGotServiceExceptionFromService() throws Exception{
		MockedDatabase.initDatabase();
		when(weatherInformationService.getAvailableCities()).thenThrow(new ServiceException());
		mockMvc.perform(MockMvcRequestBuilders.get("/availablecities"))
						.andExpect(MockMvcResultMatchers.status().isInternalServerError())
						.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
						.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ERROR_SERVICE_EXCEPTION))
						.andDo(MockMvcResultHandlers.print());
	}

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and WeatherInformationController,
	 make sure DataQueryExceptioncan can be thrown to WeatherInformationController from weatherInformationService
	 when call getAvailableCities method
	 */
	@Test
	public void whenQueryAvailableCitiesControllerGotDataQueryExceptionFromService() throws Exception{
		MockedDatabase.initDatabase();
		when(weatherInformationService.getAvailableCities()).thenThrow(new DataQueryException());
		mockMvc.perform(MockMvcRequestBuilders.get("/availablecities"))
						.andExpect(MockMvcResultMatchers.status().isInternalServerError())
						.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
						.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ERROR_DAO_EXCEPTION))
						.andDo(MockMvcResultHandlers.print());
	}
	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and WeatherInformationController,
	 make sure unknown exceptions can be thrown to weatherInformationService from weatherRecordDAO
	 when call getAvailableCities method
	 */
	@Test
	public void whenQueryAvailableCitiesControllerFotUnknownExceptionFromService () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherInformationService.getAvailableCities()).thenThrow(new Exception());
		mockMvc.perform(MockMvcRequestBuilders.get("/availablecities"))
						.andExpect(MockMvcResultMatchers.status().isInternalServerError())
						.andExpect(MockMvcResultMatchers.jsonPath("$.messageType").value(Constants.MASSAGE_TYPE_ERROR))
						.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.ERROR_UNKNOW_EXCEPTION))
						.andDo(MockMvcResultHandlers.print());
	}

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and weatherRecordDAO,
	 make sure  DataQueryException can be thrown to weatherInformationService from weatherRecordDAO
	 when call getAvailableCities method
	 */
	@Test
	public void whenQueryAvailableCitiesServiceGotDataQueryExceptionFromDao () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherRecordDaoForServiceTest.getAvailableCities()).thenThrow(new DataQueryException());
		Assert.assertThrows(DataQueryException.class, ()->weatherInformationServiceForServiceTest.getAvailableCities());
	}

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and weatherRecordDAO,
	 make sure unknown exceptions can be thrown to weatherInformationService from weatherRecordDAO
	 when call getAvailableCities method
	 */
	@Test
	public void whenQueryAvailableCitiesServiceGotUnknownExceptionFromDao () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherRecordDaoForServiceTest.getAvailableCities()).thenThrow(new Exception());
		Assert.assertThrows(Exception.class, ()->weatherInformationServiceForServiceTest.getAvailableCities());
	}

	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and weatherRecordDAO,
	 make sure DataQueryException can be thrown to weatherInformationService from weatherRecordDAO
	 when call queryWeatherByCities method
	 */
	@Test
	public void whenQueryWeatherInformationServiceGotDataQueryExceptionFromDao () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherRecordDaoForServiceTest.queryWeatherByCities(anyList())).thenThrow(new DataQueryException());
		Assert.assertThrows(Exception.class, ()->weatherInformationServiceForServiceTest.queryWeatherByCities(anyList()));
	}
	/**
	 @author: matthew.yiqing.zhu
	 @date: May 1st 2024
	 @description: Test case between weatherInformationService and weatherRecordDAO,
	 make sure unknown exceptions can be thrown weatherInformationService from weatherRecordDAO
	 when call queryWeatherByCities method
	 */
	@Test
	public void whenQueryWeatherInformationServiceGotUnknownExceptionFromDao () throws Exception{
		MockedDatabase.initDatabase();
		when(weatherRecordDaoForServiceTest.queryWeatherByCities(anyList())).thenThrow(new Exception());
		Assert.assertThrows(Exception.class, ()->weatherInformationServiceForServiceTest.queryWeatherByCities(anyList()));
	}
}
