package com.klezovich.robot.command;

import com.klezovich.robot.Robot;
import com.klezovich.robot.command.exception.CommandValidationException;

public class WaitCommand extends Command {

	private static final String tag="WAIT";
	private String[] args;
	
	public WaitCommand( String[] args ) {
		super(args);
	}
	
	@Override
	public boolean execute(Robot r) {
	  r.sleep();
	  return true;	
	}

	@Override
	protected boolean validate() {
		return true;
	}
	
}