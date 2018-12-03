package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Orientation;
import com.klezovich.robot.domain.Robot;

public abstract class Command {

	protected String[] args;
	private   Integer  lineNum;
	private final String name = "GENERIC_COMMAND_NAME";
	
	public Command( String[] args ) {
		this.args = args;
		validate();
		initializeFields();
	}
	
	public abstract Coordinates execute(Robot r);
	
	protected abstract boolean validate();
	protected abstract boolean initializeFields();
	
	protected Integer getArgNum() {
		return args.length;
	}

	public String getName() {
		return name;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	

	
}
