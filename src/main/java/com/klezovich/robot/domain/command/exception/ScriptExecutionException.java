package com.klezovich.robot.domain.command.exception;

public class ScriptExecutionException extends RuntimeException {

	Integer lineNum;
	String error;
	
	public String toString() {
		
		String res="";
		String prefix = "Script execution exception.";
		
		if(lineNum == null )
			return prefix + error;
		
		
		return prefix+" Line " + lineNum + ": "+error;
	}
	
	public ScriptExecutionException( int lineNum, String error ){
		this.lineNum = lineNum;
		this.error = error;
	}

	public ScriptExecutionException( String error ){
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
