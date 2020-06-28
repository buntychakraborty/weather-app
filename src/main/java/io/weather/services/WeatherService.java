package io.weather.services;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.weather.models.DailyTempBO;
import io.weather.restclients.WeatherRestClient;

@Service
public class WeatherService {
	@Autowired
	private WeatherRestClient weatherRestClient;

	@Value("${api-key}")
	private String apiKey;
	@Value("${exclude}")
	private String exclude;

	public Long convertKelvintoClesius(Double tempInKelvin) {
		Double convertedValue = tempInKelvin - 273.15d;
		return convertedValue.longValue();
	}

	public static Long covertWindSpeedFromMetersPerSecondToKiloMetersPerHour(Double windSpeedInMeterPerSecond) {

		Double convertedValue = windSpeedInMeterPerSecond * 3.6;
		return convertedValue.longValue();

	}

	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public DailyTempBO getDailyTemperatureFromLatAndLong(String lat, String lon) {
		return weatherRestClient.getDailyTemperatureFromLatAndLong(lat, lon, apiKey, exclude);

	}
	
	public String getWeatherOfParticularLocationForName(String lat, String lon) {
		return getFilteredNameOfLocation(weatherRestClient.getWeatherOfParticularLocationForName(apiKey,lat, lon).getName());

	} 

	public static String getDateInRequiredFormat(Date date) {

		LocalDateTime localDateTime = convertToLocalDateTimeViaInstant(date);
		String monthName = localDateTime.getMonth().toString().charAt(0)
				+ (localDateTime.getMonth().toString().toLowerCase().substring(1, 3));

		String finalDate = localDateTime.getDayOfMonth() + " " + monthName;

		return finalDate;

	}
	
	public static String getFilteredNameOfLocation(String locationName) {
		
        String normalizedLocationName = Normalizer.normalize(locationName, Normalizer.Form.NFD);
        String accentRemovedLocationName = normalizedLocationName.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return accentRemovedLocationName;
	}
}
