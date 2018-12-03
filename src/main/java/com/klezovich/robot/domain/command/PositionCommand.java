package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Orientation;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.command.exception.CommandValidationException;

public class PositionCommand extends Command {

	private final String name = "POSITION";
	
	private Coordinates coordinates;
		
	public PositionCommand( String[] args ) {
		super(args);
	}
	
	@Override
	public Coordinates execute(Robot r) {
	    return r.setCoordinates(coordinates);
	}

	@Override
	protected boolean validate() {
		
		if( 3 != getArgNum() )
			throw new CommandValidationException(this, "command expects 3 argumentss");
		
		Integer x = null;
		try {
			x = Integer.parseInt(args[0]);
		}catch( NumberFormatException nfe ) {
			throw new CommandValidationException(this, "first argument must be an integer");
		}
		
		if( x < 0 )
			throw new CommandValidationException(this, "first argument must be non-negative");
		
		Integer y = null;
		try {
			y = Integer.parseInt(args[1]);
		}catch( NumberFormatException nfe ) {
			throw new CommandValidationException(this, "second argument must be an integer");
		}
		
		if( y < 0 )
			throw new CommandValidationException(this, "second argument must be non-negative");
		
		Orientation orient=null;
		try {
			orient = Orientation.valueOf(args[2]);
		}catch( IllegalArgumentException e ) {
			throw new CommandValidationException(this, "third argument must be an orientation");
		}
		
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
