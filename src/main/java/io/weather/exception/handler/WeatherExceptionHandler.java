package io.weather.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WeatherExceptionHandler extends ResponseEntityExceptionHandler {

	 @ExceptionHandler(Exception.class)
	    public final ModelAndView handleAllExceptions(Exception ex, WebRequest request) {
		 System.out.println(request.getDescription(true));
	        return new ModelAndView("error");
	    }
}
