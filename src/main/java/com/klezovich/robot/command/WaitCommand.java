package com.klezovich.robot.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.Coordinates;
import com.klezovich.robot.Robot;

public class WaitCommand extends Command {

	private static final Map<Integer,Class> argDefinitions = new HashMap<>();
	private static final String name="WAIT";
	
	public WaitCommand( String[] args ) {
		super(args);
	}
	
	@Override
	public Coordinates execute(Robot r) {
        return  r.getCoordinates();
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
