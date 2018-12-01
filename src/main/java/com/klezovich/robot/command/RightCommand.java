package com.klezovich.robot.command;

import com.klezovich.robot.Robot;

public class RightCommand extends Command {

	private static final String tag="RIGHT";
	private String[] args;
	
	public RightCommand( String[] args ) {
		super(args);
	}
	
	protected boolean validate() {
		return true;
	}
	
	public boolean execute(Robot r) {
	  return true;	
	}
	
}
