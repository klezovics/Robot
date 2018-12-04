package com.klezovich.robot.domain.command;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.command.exception.CommandValidationException;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class ForwardCommand extends Command {

	private Integer distance;

	public ForwardCommand(String[] args) {
		super(args);
		this.name = "FORWARD";
		validate();
	}

	@Override
	public Coordinates execute(Robot r) {

		try {
			return r.moveForward(distance);
		} catch (Robot.RobotControlException e) {
			throw new ScriptExecutionException(this, e.getMessage());
		}
	}

	@Override
	protected boolean validate() {

		if (args.length != 1)
			throw new CommandValidationException(this, "command expects one argument");

		Integer distance = null;
		try {
			distance = Integer.parseInt(args[0]);
		} catch (NumberFormatException nfe) {
			throw new CommandValidationException(this, "first argument must be an integer");
		}
		

		if (distance < 0)
			throw new CommandValidationException(this, "first argument must be non-negative");

		this.distance = distance;
		
		return true;
	}

}
