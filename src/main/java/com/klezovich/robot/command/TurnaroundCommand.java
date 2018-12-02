package com.klezovich.robot.command;

import com.klezovich.robot.Coordinates;
import com.klezovich.robot.Direction;
import com.klezovich.robot.Robot;

public class TurnaroundCommand extends Command {

	private static final String name="TURNAROUND";
	private String[] args;
	
	public TurnaroundCommand( String[] args ) {
		super(args);
	}
	
	@Override
	public Coordinates execute(Robot r) {
	
	  Direction rotationDirection = Direction.getRandomLeftOrRight();
	
	  r.rotate( rotationDirection );
	  r.rotate( rotationDirection );
	
	  return r.getCoordinates();
	  
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
