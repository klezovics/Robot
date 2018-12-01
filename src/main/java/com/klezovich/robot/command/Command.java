package com.klezovich.robot.command;

import com.klezovich.robot.Robot;
import com.klezovich.robot.command.exception.CommandValidationException;

public abstract class Command {

	private static final String tag=null;
	private String[] args;
	
	public Command( String[] args ) {
		this.args = args;
		validate();
	}
	
	protected abstract boolean validate();
	public abstract boolean execute(Robot r);
	
	protected boolean argListEmpty() {
		if( args.length == 0 )
			return true;
		
		return false;
	}
	
	protected String formArgsForNoArgCmdErrorMsg() {
		return "Command " + tag + " does not take any arguments"; 
	}
	
	protected CommandValidationException formArgsForNoArgCmdException() {
		return new CommandValidationException(formArgsForNoArgCmdErrorMsg());
	}
	
}
