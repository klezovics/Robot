package com.klezovich.robot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.command.Command;
import com.klezovich.robot.domain.command.CommandParser;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;
import com.klezovich.robot.json.JsonErrorDto;

@Controller
public class BackEndController {

	@PostMapping(value = "/robot_control_page/robots/")
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

		if (commands == null || commands.size() == 0 )
			return new JsonErrorDto("The script contains no commands to process");

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
