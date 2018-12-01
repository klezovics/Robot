package com.klezovich.robot.command.exception;


public class CommandValidationException extends RuntimeException {

	private int line; 
	String error; 
	
	CommandValidationException( int line, String error ){
		this.line = line;
		this.error = error;
	}

	@Override
	public String toString() {
		return "CommandValidationException: Error in line "+line+" :"+error;
	}
	
	
}
