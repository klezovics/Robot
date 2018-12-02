package com.klezovich.robot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.command.Command;
import com.klezovich.robot.domain.command.CommandParser;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;
import com.klezovich.robot.json.JsonErrorDto;

@Controller
public class AppController {

	@GetMapping("/")
	public String start() {
		return "login";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	/*@PostMapping("/login")
	public String processLogin( Model m, Principal p ) {
		m.addAttribute("script", new Script());

		if (p != null)
			m.addAttribute("userName", p.getName());
		else
			m.addAttribute("userName", "Anonymous");
		return "robot_control_page";
	}*/

	@GetMapping("/robot_control_page")
	public String getRobotControlPage(Model m, Principal p) {
		m.addAttribute("script", new Script());

		if (p != null)
			m.addAttribute("userName", p.getName());
		else
			m.addAttribute("userName", "Anonymous");
		return "robot_control_page";
	}

	@PostMapping(value = "/robots/")
	@ResponseBody
	public Object getRobotMovements(@RequestBody String str, ModelMap m) {
		System.out.println("Hello from robots controller");
		System.out.println(m);
		System.out.println("String is:" + str);

		CommandParser parser = new CommandParser(str);

		List<Command> commands = null;
		try {
			commands = parser.parseScript();
		} catch (ScriptExecutionException e) {
			System.out.println("EXCEPTION - NO COMMANDS FOR YOU");
			System.out.println(e);
			return new JsonErrorDto(e.toString());
		}

		if (commands == null)
			return null;

		System.out.println("Number of commands:" + commands.size());
		Robot r = new Robot();
		try {
			for (Command command : commands) {
				System.out.println(command);
				command.execute(r);
			}
		} catch (ScriptExecutionException e) {
			return new JsonErrorDto(e.toString());
		}

		return r.getCoordinates();
	}

}
