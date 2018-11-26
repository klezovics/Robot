package com.klezovich.robot;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@GetMapping("/")
	public String getHomepage( Model m ) {
		m.addAttribute("script", new Script() );
		return "index";
	}
	
	@PostMapping("/")
	public String processScript( @Valid @ModelAttribute("script") Script script, Model m ) {
		System.out.println(script);
		return "index";
	}
}
