package com.springsecurity.demo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@GetMapping("/")
	public String showHome() {
		LOGGER.log(Level.INFO, "Home Page");
		return "home";
	}

	@GetMapping("/leaders")
	public String showLeaders() {

		return "leaders";
	}

	@GetMapping("/admins")
	public String showAdmins() {

		return "admins";
	}
	
}
