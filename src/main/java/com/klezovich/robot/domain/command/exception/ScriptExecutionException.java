package com.klezovich.robot.domain.command.exception;

import com.klezovich.robot.domain.command.Command;

public class ScriptExecutionException extends RuntimeException {

	Integer lineNum;
	String cmdName;
	String error;
	
	public String toString() {
		
		if(cmdName == null )
			return "Line " + lineNum + ": " + error;
		
		return "Line "+lineNum+ " command "+cmdName+": "+error;
	}
	
	public ScriptExecutionException( String error ){
		this.error = error;
	}

	public ScriptExecutionException( Command cmd, String error ){
		this.lineNum = cmd.getLineNum();
		this.cmdName = cmd.getName();
		this.error = error;
	}

	public ScriptExecutionException( Integer lineNum, String error ){
		this.lineNum = lineNum;
		this.error = error;
	}

	public ScriptExecutionException(Integer lineNum, String cmdName, String error) {
		super();
		this.lineNum = lineNum;
		this.cmdName = cmdName;
		this.error = error;
	}

	public String getCmdName() {
		return cmdName;
	}

	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	
	
	
	

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	
	
}
