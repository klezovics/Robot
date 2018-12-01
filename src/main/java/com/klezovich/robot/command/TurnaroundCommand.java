package com.klezovich.robot.command;

import com.klezovich.robot.Direction;
import com.klezovich.robot.Robot;

public class TurnaroundCommand extends Command {

	private static final String tag="TURNAROUND";
	private String[] args;
	
	public TurnaroundCommand( String[] args ) {
		super(args);
	}
	
	@Override
	public boolean execute(Robot r) {
	
	  Direction rotationDirection = Direction.getRandomLeftOrRight();
	
	  r.rotate( rotationDirection );
	  r.rotate( rotationDirection );
	
	  return true;	
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
