package com.klezovich.robot.json;

import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class JsonErrorDto {

	String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public JsonErrorDto(String error) {
		this.error = error;
	}
	
	public JsonErrorDto(ScriptExecutionException e ) {
		this.error = e.toString();
	}
	
	
}
