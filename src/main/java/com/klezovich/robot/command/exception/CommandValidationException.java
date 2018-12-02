package com.klezovich.robot.command.exception;


public class CommandValidationException extends ScriptExecutionException {
 	
	public CommandValidationException( String error ){
		super(error);
	}
	
}
