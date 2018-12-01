package com.klezovich.robot.command;

import com.klezovich.robot.Coordinates;
import com.klezovich.robot.Orientation;
import com.klezovich.robot.Robot;

public class PositionCommand extends Command {

	private static final String tag = "POSITION";
	private String[] args;
	
	
	private Coordinates coordinates;
	private int x; 
	private int y; 
	private Orientation orientation;
	
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
	public boolean execute(Robot r) {
	    r.setCoordinates(coordinates);
	    return true;
	}

	@Override
	protected boolean validateArguments() {
		return true;
	}
	
	@Override
	protected boolean initializeFields() {
		coordinates.setX( Integer.valueOf(args[0]) ); 
		coordinates.setY( Integer.valueOf(args[1]) ); 
		coordinates.setOrientation( Orientation.valueOf(args[2]) );
		return true;
	}
	
}
