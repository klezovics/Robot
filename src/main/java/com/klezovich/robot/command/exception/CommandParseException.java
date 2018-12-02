package com.klezovich.robot.command.exception;


public class CommandParseException extends RuntimeException {
 
	String error; 
	String cmdTag="";
	
	public CommandParseException( String error ){
		this.error = error;
	}

	public void setCmdTag( String cmdTag ) {
		this.cmdTag = cmdTag;
	}
	
	@Override
	public String toString() {
		return "Command " + cmdTag+":"+error;
	}	
	
}
