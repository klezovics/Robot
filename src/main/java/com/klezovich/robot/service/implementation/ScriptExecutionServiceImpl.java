package com.klezovich.robot.service.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.command.Command;
import com.klezovich.robot.domain.command.CommandParser;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;
import com.klezovich.robot.service.ScriptExecutionService;

@Component
public class ScriptExecutionServiceImpl implements ScriptExecutionService {

	@Override
	public Coordinates executeScript(Script script) {

		CommandParser parser = new CommandParser(script.getText());

		List<Command> commands = null;
		commands = parser.parseScript();

		if (commands.size() == 0)
			throw new ScriptExecutionException("The script contains no commands to process");

		System.out.println("Number of commands:" + commands.size());
		Robot r = new Robot();

		for (Command command : commands) {
			System.out.println(command);
			command.execute(r);
		}

		return r.getCoordinates();

	}

}
