package co.nz.westpac.interview.simpleweatherinformationservice;

import co.nz.westpac.interview.simpleweatherinformationservice.util.MockedDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleWeatherInformationServiceApplication {

	public static void main(String[] args) {
		MockedDatabase.initDatabase();
		SpringApplication.run(SimpleWeatherInformationServiceApplication.class, args);
	}

}
