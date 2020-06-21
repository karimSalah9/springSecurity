package com.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LoginController {

	@GetMapping("/showMyloginPage")
	public String showMyloginPage() {

		return "plain-login";
	}
}
