package com.klezovich.robot.service.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Orientation;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.command.Command;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;
import com.klezovich.robot.domain.command.parser.CommandParser;
import com.klezovich.robot.service.ScriptExecutionService;

@Component
public class ScriptExecutionServiceImpl implements ScriptExecutionService {

	@Override
	public Coordinates executeScript(Script script) throws ScriptExecutionException {

		List<Command> commands = CommandParser.parseScript(script);

		int maxX = script.getMaxX();
		int maxY = script.getMaxY();
		
		Robot r = new Robot(maxX,maxY);

		for (Command command : commands) {
			command.execute(r);
		}

		return r.getCoordinates();

	}

}
