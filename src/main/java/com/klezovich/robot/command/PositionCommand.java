package com.klezovich.robot.command;

import com.klezovich.robot.Orientation;
import com.klezovich.robot.Robot;

public class PositionCommand extends Command {

	private static final String tag = "POSITION";
	private String[] args;
	
	// Describing argument list
	static {
		addArgumentDefinition(0, Integer.class );
		addArgumentDefinition(1, Integer.class );
		addArgumentDefinition(2, Orientation.class );
	}
	
	public PositionCommand( String[] args ) {
		super(args);
	}
	
	@Override
	protected boolean validate() {
		return true;
	}
	
	@Override
	public boolean execute(Robot r) {
	  return true;	
	}
	
}
