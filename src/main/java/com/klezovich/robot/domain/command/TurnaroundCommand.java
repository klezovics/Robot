package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Direction;
import com.klezovich.robot.domain.Robot;

public class TurnaroundCommand extends Command {
	
	private static final Map<Integer,Class> argDefinitions = new HashMap<>();
	private static final String name="TURNAROUND";
	

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
	protected boolean validate() {
		return true;
	}
	
	@Override
	protected boolean initializeFields() {
		return true;
	}
	
}