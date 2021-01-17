package com.baeldung.springsecuritythymeleaf.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//	AuthenticationCredentialsNotFoundException

	@ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
	public void defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

		System.out.println("Mare infractor");
	}
}
