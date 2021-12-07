package com.yeqing.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class HandlerExceptionControllerAdvice {
    
	@ExceptionHandler
	public String error(Exception ex, Model m) {
		m.addAttribute("errorMsg", ex.getMessage());
		return "/commons/error";
	}
}
