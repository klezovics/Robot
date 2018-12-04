package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Orientation;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.Robot.RobotControlException;
import com.klezovich.robot.domain.command.exception.CommandValidationException;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class PositionCommand extends Command {

	
	private Coordinates coordinates;
		
	public PositionCommand( String[] args, Integer lineNumber ) {
		super(args, lineNumber );
		this.name="POSITION";
		validate();
	}
	
	@Override
	public Coordinates execute(Robot r) {
		
		try {
	      return r.setCoordinates(coordinates);
		}catch( RobotControlException e ) {
			throw new ScriptExecutionException(this,e.getMessage());
		}
		
	}

	@Override
	protected boolean validate() {
		
		if( 3 != getArgNum() )
			throw new CommandValidationException(this, "command expects 3 arguments");
		
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
		
		Orientation orientation=null;
		try {
			orientation = Orientation.valueOf(args[2]);
		}catch( IllegalArgumentException e ) {
			throw new CommandValidationException(this, "third argument must be an orientation");
		}
		
		coordinates = new Coordinates(x,y,orientation);
		return true;
	}
	
}
