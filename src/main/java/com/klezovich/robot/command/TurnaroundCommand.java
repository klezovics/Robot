package com.klezovich.robot.command;

import com.klezovich.robot.Robot;

public class TurnaroundCommand extends Command {

	private static final String tag="TURNAROUND";
	private String[] args;
	
	public TurnaroundCommand( String[] args ) {
		super(args);
	}
	
	protected boolean validate() {
		return true;
	}
	
	public boolean execute(Robot r) {
	  return true;	
	}
	
}
