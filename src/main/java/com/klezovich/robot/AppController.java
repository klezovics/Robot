package com.klezovich.robot;

import org.springframework.web.bind.annotation.GetMapping;

public class AppController {

	@GetMapping("/")
	public String getHomepage() {
		return "index";
	}
}
