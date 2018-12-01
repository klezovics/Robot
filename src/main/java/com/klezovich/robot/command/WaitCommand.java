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

	  return true;	
	}

	@Override
	protected boolean validateArguments() {
		return true;
	}

	@Override
	protected boolean initializeFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
