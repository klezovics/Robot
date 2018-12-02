package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Direction;
import com.klezovich.robot.domain.Robot;

public class RightCommand extends Command {

	private static final String name = "RIGHT";
	
	public RightCommand(String[] args) {
		super(args);
	}

	@Override
	public Coordinates execute(Robot r) {
		
		return r.rotate(Direction.RIGHT);
	}

	@Override
	protected boolean validate() {
		return true;
	}

	@Override
	protected boolean initializeFields() {
		return true;
	}

}
