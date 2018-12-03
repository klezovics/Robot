package com.klezovich.robot.domain.command.exception;


public class ArgsForZeroArgCommandException extends ScriptExecutionException {
 	
	private String cmdName;
	
	public ArgsForZeroArgCommandException( String cmdName, String error ){
		super(error);
		this.cmdName = cmdName;
	}
	
	
	
}
