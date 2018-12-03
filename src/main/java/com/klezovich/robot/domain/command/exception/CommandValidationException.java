package com.klezovich.robot.domain.command.exception;

import com.klezovich.robot.domain.command.Command;

public class CommandValidationException extends ScriptExecutionException {
 	
	public CommandValidationException( Command cmd, String error ){
		super(error);
		setLineNum(cmd.getLineNum());
		setCmdName(cmd.getName());
	}
	
	public CommandValidationException(  String error ){
		super(error);
	}
	
	
	
}
