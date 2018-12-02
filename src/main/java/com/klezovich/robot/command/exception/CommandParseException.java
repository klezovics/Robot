package com.klezovich.robot.command.exception;


public class CommandParseException extends RuntimeException {
 
	String error; 
	String cmdName="";
	Integer lineNumber;
	
	public CommandParseException( String error ){
		this.error = error;
	}
	
	public CommandParseException( String error, String cmdName, Integer lineNumber ){	
		this.error = error;
		this.cmdName = cmdName;
		this.lineNumber = lineNumber;
	}

	public void setCmdName( String cmdName ) {
		this.cmdName = cmdName;
	}
	
	@Override
	public String toString() {
		
		if( lineNumber == null )
		  return "Command " + cmdName+":"+error;
		
		return "Line " + lineNumber + ":" + " command " + cmdName + " error " + error;
	}	
	
}
