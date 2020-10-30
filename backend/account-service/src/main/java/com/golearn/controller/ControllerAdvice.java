package com.golearn.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(SQLException.class)
	public String sqlError() {
		return "SQL Error";
	}
}
