package br.com.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.api.exceptions.ClienteNotFoundException;

@ControllerAdvice
public class ClienteNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(ClienteNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(ClienteNotFoundException ex) {
		return ex.getMessage();
	}
}
