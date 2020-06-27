package io.weather.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.weather.models.DailyTempBO;
import io.weather.models.WeatherBO;

@FeignClient(url="http://api.openweathermap.org/data/2.5/",name="weather-client")
public interface WeatherRestClient {
	@GetMapping("/weather")
	public WeatherBO getWeatherOfParticularLocation(@RequestParam(value="appid") String appid,@RequestParam(value="q") String locationName);
	
	@GetMapping("/onecall")
	public DailyTempBO getDailyTemperatureFromLatAndLong(@RequestParam(value="lat") String lat,@RequestParam(value="lon") String lon,
			@RequestParam(value="appid") String appid,@RequestParam(value="exclude") String exclude);
	
	

}
