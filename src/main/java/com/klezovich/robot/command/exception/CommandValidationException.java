package com.klezovich.robot.command.exception;


public class CommandValidationException extends RuntimeException {
 
	String error; 
	
	public CommandValidationException( String error ){
		this.error = error;
	}

	@Override
	public String toString() {
		return "CommandValidationException:"+error;
	}	
	
}
