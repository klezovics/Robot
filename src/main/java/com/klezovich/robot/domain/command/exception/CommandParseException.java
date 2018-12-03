package com.klezovich.robot.domain.command.exception;


public class CommandParseException extends ScriptExecutionException {
 
	public CommandParseException( String error, String cmdName ){
		super(error);
		setCmdName(cmdName);
	}
	
		
}
