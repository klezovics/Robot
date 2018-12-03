package com.klezovich.robot.domain.command;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.command.exception.CommandValidationException;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class ForwardCommand extends Command {

	private static final String name="FORWARD";
	
	private Integer distance;
	
	

	public ForwardCommand( String[] args ) {
		super(args);
	}
	
	@Override
	public Coordinates execute(Robot r) {
	
	 try {
	    return r.moveForward( distance );	
	 }catch( Robot.RobotException e ) {
		 throw new ScriptExecutionException(this, e.getMessage() );
	 }
	}

	@Override
	protected boolean validate() {
		
		if( args.length != 1 )
			throw new CommandValidationException(name + "command expects one argument");
		
		try {
			Integer i = Integer.parseInt(args[0]);
		}catch( NumberFormatException nfe ) {
			throw new CommandValidationException("First argument of "+ name + " command must be an integer");
		}
		
		return true;
	}
	
	@Override
	protected boolean initializeFields() {
		
		
		distance = Integer.valueOf(args[0]);
		
		return true;
	}
	
}
