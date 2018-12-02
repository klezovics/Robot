package com.klezovich.robot.command;

import com.klezovich.robot.Coordinates;
import com.klezovich.robot.Robot;

public class WaitCommand extends Command {

	private static final String name="WAIT";
	private String[] args;
	
	public WaitCommand( String[] args ) {
		super(args);
	}
	
	@Override
	public Coordinates execute(Robot r) {
        return  r.getCoordinates();
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
