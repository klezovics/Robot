package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Orientation;
import com.klezovich.robot.domain.Robot;

public class PositionCommand extends Command {

	private static final String name = "POSITION";
	
	private Coordinates coordinates;
	private int x; 
	private int y; 
	private Orientation orientation;
	
	
	public PositionCommand( String[] args ) {
		super(args);
	}
	
	@Override
	public Coordinates execute(Robot r) {
	    return r.setCoordinates(coordinates);
	}

	@Override
	protected boolean validate() {
		return true;
	}
	
	@Override
	protected boolean initializeFields() {
		coordinates = new Coordinates();
		coordinates.setX( Integer.valueOf(args[0]) ); 
		coordinates.setY( Integer.valueOf(args[1]) ); 
		coordinates.setOrientation( Orientation.valueOf(args[2]) );
		return true;
	}
	
}
