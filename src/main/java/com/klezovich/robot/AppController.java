package com.klezovich.robot;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klezovich.robot.command.Command;
import com.klezovich.robot.command.CommandParser;
import com.klezovich.robot.command.exception.CommandParseException;

@Controller
public class AppController {

	@GetMapping("/")
	public String getHomepage( Model m ) {
		m.addAttribute("script", new Script() );
		return "index";
	}
	
	
	@PostMapping(value="/robots")
	@ResponseBody
	public Coordinates getRobotMovements( @RequestBody String str , ModelMap m ) {
		//System.out.println(m);
		//System.out.println("String is:" + str);
		
		CommandParser parser = new CommandParser( str );
		
		
		List<Command> commands = null;
		try {
		   commands = parser.parseScript(); 
		}catch( CommandParseException e ) {
			System.out.println("EXCEPTION - NO COMMANDS FOR YOU");
		   	System.out.println(e);
		}
		
		if( commands == null )
			return null;
		
		System.out.println("Number of commands:" + commands.size() );
		Robot r = new Robot();
		for( Command command : commands ) {
			System.out.println(command);
			command.execute(r);
		}
		
		return r.getCoordinates();
	}
	
	
}
