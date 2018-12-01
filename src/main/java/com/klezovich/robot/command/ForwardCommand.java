package com.klezovich.robot.command;

import com.klezovich.robot.Robot;

public class ForwardCommand extends Command {

	private static final String tag="FORWARD";
	private String[] args;
	
	public ForwardCommand( String[] args ) {
		super(args);
	}
	
	protected boolean validate() {
		
		
		return true;
	}
	
	public boolean execute(Robot r) {
	  return true;	
	}
	
}
