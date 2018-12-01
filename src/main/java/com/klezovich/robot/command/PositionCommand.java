package com.klezovich.robot.command;

import com.klezovich.robot.Robot;

public class PositionCommand extends Command {

	private static final String tag = "POSITION";
	private String[] args;
	
	public PositionCommand( String[] args ) {
		super(args);
	}
	
	protected boolean validate() {
		return true;
	}
	
	public boolean execute(Robot r) {
	  return true;	
	}
	
}
