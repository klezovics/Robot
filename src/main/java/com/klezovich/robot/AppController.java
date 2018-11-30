package com.klezovich.robot;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

	@GetMapping("/")
	public String getHomepage( Model m ) {
		m.addAttribute("script", new Script() );
		return "index";
	}
	
	@PostMapping("/")
	public String processScript( @Valid @ModelAttribute("script") Script script, Model m ) {
		//System.out.println(script);
		return "index";
	}
	
	@PostMapping(value="/robots")
	@ResponseBody
	public Coordinates getRobotMovements( @RequestBody String str , ModelMap m ) {
		System.out.println(m);
		System.out.println("String is:" + str);
		return new Coordinates(0,1,Orientation.WEST);
	}
	
	
}
