package com.klezovich.robot.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.Coordinates;
import com.klezovich.robot.Direction;
import com.klezovich.robot.Robot;

public class LeftCommand extends Command {
	
	private static final String name="LEFT";
	
	private String[] args;
	
	public LeftCommand( String[] args ) {
		super(args);
	}	
	
	@Override
	public Coordinates execute(Robot r) {
	
	  return r.rotate( Direction.LEFT );
	  	
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
