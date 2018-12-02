package com.klezovich.robot.command.exception;

public class ScriptExecutionException extends RuntimeException {

	int lineNum;
	String error;
	
	public String toString() {
		return "Script execution exception. Error in line " + lineNum + ":"+error;
	}
	
	ScriptExecutionException( int lineNum, String error ){
		this.lineNum = lineNum;
		this.error = error;
	}

	ScriptExecutionException( String error ){
		this.lineNum = lineNum;
		this.error = error;
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
