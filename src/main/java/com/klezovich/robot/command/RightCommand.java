package com.klezovich.robot.command;

import com.klezovich.robot.Coordinates;
import com.klezovich.robot.Direction;
import com.klezovich.robot.Robot;

public class RightCommand extends Command {

	private static final String name = "RIGHT";
	private String[] args;

	public RightCommand(String[] args) {
		super(args);
	}

	@Override
	public Coordinates execute(Robot r) {
		
		return r.rotate(Direction.RIGHT);
	}

	@Override
	protected boolean validateArguments() {
		return true;
	}

	@Override
	protected boolean initializeFields() {
		return true;
	}

}
