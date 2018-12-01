package com.klezovich.robot.command;

import com.klezovich.robot.Robot;

public class WaitCommand extends Command {

	private static final String tag="WAIT";
	private String[] args;
	
	public WaitCommand( String[] args ) {
		super(args);
	}
	
	protected boolean validate() {
		return true;
	}
	
	public boolean execute(Robot r) {
	  return true;	
	}
	
}
