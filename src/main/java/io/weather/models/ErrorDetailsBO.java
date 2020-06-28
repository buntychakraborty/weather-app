package io.weather.models;

import org.springframework.http.HttpStatus;

public class ErrorDetailsBO {
	private String cod;
	private String message;
	public ErrorDetailsBO(HttpStatus internalServerError, String description) {
		// TODO Auto-generated constructor stub
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
