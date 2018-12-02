package com.klezovich.robot.command.exception;


public class CommandValidationException extends CommandParseException {
 
	String error; 
	String cmdTag="";
	
	public CommandValidationException( String error ){
		super(error);
	}

	public void setCmdTag( String cmdTag ) {
		this.cmdTag = cmdTag;
	}
	
	@Override
	public String toString() {
		return "Command " + cmdTag+":"+error;
	}	
	
}
