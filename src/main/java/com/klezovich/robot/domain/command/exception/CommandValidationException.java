package com.klezovich.robot.domain.command.exception;


public class CommandValidationException extends ScriptExecutionException {
 	
	public CommandValidationException( String error ){
		super(error);
	}
	
}
