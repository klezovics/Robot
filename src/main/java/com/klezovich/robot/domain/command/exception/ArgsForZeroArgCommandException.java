package com.klezovich.robot.domain.command.exception;

import com.klezovich.robot.domain.command.Command;

public class ArgsForZeroArgCommandException extends CommandValidationException {
 		
	public ArgsForZeroArgCommandException( Command cmd ){
		super("arguments provided for zero argument command");
		setLineNum(cmd.getLineNum());
		setCmdName(cmd.getName());
	}
	
	
	
}
