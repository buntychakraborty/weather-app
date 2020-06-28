package io.weather.controllers;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.weather.models.Daily;
import io.weather.models.DailyTempBO;
import io.weather.models.WeatherBO;
import io.weather.restclients.WeatherRestClient;
import io.weather.services.WeatherService;

@RestController
@RequestMapping("/")
public class WeatherController {

	@Autowired
	private WeatherRestClient weatherRestClient;
	@Value("${api-key}")
	private String apiKey;
	@Value("${exclude}")
	private String exclude;

	@Autowired
	private WeatherService weatherService;

	@GetMapping("/weather")
	public ModelAndView getWeather(@RequestParam(value = "location") String location) {
		WeatherBO weatherReportData = weatherRestClient.getWeatherOfParticularLocation(apiKey, location);
		ModelAndView modelAndViewForIndexPage = new ModelAndView("showweather");
		String finalDateWithMonthName = WeatherService
				.getDateInRequiredFormat(new Date(weatherReportData.getDt() * 1000l));
		LocalDateTime localDateTime = WeatherService
				.convertToLocalDateTimeViaInstant(new Date(weatherReportData.getDt() * 1000l));
		DailyTempBO weatherBO = weatherService.getDailyTemperatureFromLatAndLong(
				weatherReportData.getCoord().getLat().toString(), weatherReportData.getCoord().getLon().toString());
		Daily weatherBO1 = weatherBO.getDaily().get(1);
		Daily weatherBO2 = weatherBO.getDaily().get(2);
		Daily weatherBO3 = weatherBO.getDaily().get(3);
		Daily weatherBO4 = weatherBO.getDaily().get(4);
		Daily weatherBO5 = weatherBO.getDaily().get(5);
		Daily weatherBO6 = weatherBO.getDaily().get(6);
		modelAndViewForIndexPage.addObject("nameOfTheLocation", WeatherService.getFilteredNameOfLocation(weatherReportData.getName()));
		modelAndViewForIndexPage.addObject("weekDayName", localDateTime.getDayOfWeek());
		modelAndViewForIndexPage.addObject("finalDate", finalDateWithMonthName);
		modelAndViewForIndexPage.addObject("currentTemperature",
				weatherService.convertKelvintoClesius(weatherReportData.getMain().getTemp()));
		modelAndViewForIndexPage.addObject("clouds", Integer.toString(weatherReportData.getClouds().getAll()) + "%");
		modelAndViewForIndexPage.addObject("windSpeed",
				Double.toString(WeatherService
						.covertWindSpeedFromMetersPerSecondToKiloMetersPerHour(weatherReportData.getWind().getSpeed()))
						+ "km/h");
		modelAndViewForIndexPage.addObject("secondDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO1.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("secondDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO1.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("secondDayName", localDateTime.plusDays(1l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("thirdDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO2.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("thirdDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO2.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("thirdDayName", localDateTime.plusDays(2l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("fourthDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO3.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("fourthDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO3.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("fourthDayName", localDateTime.plusDays(3l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("fifthDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO4.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("fifthDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO4.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("fifthDayName", localDateTime.plusDays(4l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("sixthDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO5.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("sixthDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO5.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("sixthDayName", localDateTime.plusDays(5l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("seventhDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO6.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("seventhDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO6.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("seventhDayName", localDateTime.plusDays(6l).getDayOfWeek());

		return modelAndViewForIndexPage;
	}

	@GetMapping("/weatherCurrentLocation")
	public ModelAndView getWeatherBaseLocation(@RequestParam(value = "latitude") String lat,
			@RequestParam(value = "longitude") String lon) {
		
		String location = null;
		ModelAndView modelAndViewForIndexPage = new ModelAndView("showweather");
		DailyTempBO weatherBO = weatherService.getDailyTemperatureFromLatAndLong(lat, lon);
		location = weatherService.getWeatherOfParticularLocationForName(lat,lon);
		

		WeatherBO weatherReportData = weatherRestClient.getWeatherOfParticularLocation(apiKey, location);
		String finalDateWithMonthName = WeatherService
				.getDateInRequiredFormat(new Date(weatherReportData.getDt() * 1000l));
		LocalDateTime localDateTime = WeatherService
				.convertToLocalDateTimeViaInstant(new Date(weatherReportData.getDt() * 1000l));

		Daily weatherBO1 = weatherBO.getDaily().get(1);
		Daily weatherBO2 = weatherBO.getDaily().get(2);
		Daily weatherBO3 = weatherBO.getDaily().get(3);
		Daily weatherBO4 = weatherBO.getDaily().get(4);
		Daily weatherBO5 = weatherBO.getDaily().get(5);
		Daily weatherBO6 = weatherBO.getDaily().get(6);
		modelAndViewForIndexPage.addObject("nameOfTheLocation", location);
		modelAndViewForIndexPage.addObject("weekDayName", localDateTime.getDayOfWeek());
		modelAndViewForIndexPage.addObject("finalDate", finalDateWithMonthName);
		modelAndViewForIndexPage.addObject("currentTemperature",
				weatherService.convertKelvintoClesius(weatherReportData.getMain().getTemp()));
		modelAndViewForIndexPage.addObject("clouds", Integer.toString(weatherReportData.getClouds().getAll()) + "%");
		modelAndViewForIndexPage.addObject("windSpeed",
				Double.toString(WeatherService
						.covertWindSpeedFromMetersPerSecondToKiloMetersPerHour(weatherReportData.getWind().getSpeed()))
						+ "km/h");
		modelAndViewForIndexPage.addObject("secondDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO1.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("secondDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO1.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("secondDayName", localDateTime.plusDays(1l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("thirdDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO2.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("thirdDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO2.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("thirdDayName", localDateTime.plusDays(2l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("fourthDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO3.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("fourthDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO3.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("fourthDayName", localDateTime.plusDays(3l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("fifthDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO4.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("fifthDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO4.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("fifthDayName", localDateTime.plusDays(4l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("sixthDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO5.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("sixthDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO5.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("sixthDayName", localDateTime.plusDays(5l).getDayOfWeek());

		modelAndViewForIndexPage.addObject("seventhDayDayTemp",
				weatherService.convertKelvintoClesius(weatherBO6.getTemp().getDay()));
		modelAndViewForIndexPage.addObject("seventhDayNightTemp",
				weatherService.convertKelvintoClesius(weatherBO6.getTemp().getNight()));
		modelAndViewForIndexPage.addObject("seventhDayName", localDateTime.plusDays(6l).getDayOfWeek());

		return modelAndViewForIndexPage;
	}

	@GetMapping("/index")
	public ModelAndView getIndexPage() {
		return new ModelAndView("index");
	}

}
