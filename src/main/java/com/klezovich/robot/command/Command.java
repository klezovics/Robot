package com.klezovich.robot.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.Coordinates;
import com.klezovich.robot.Orientation;
import com.klezovich.robot.Robot;

public abstract class Command {

	private String[] args;
	
	
	
	public Command( String[] args ) {
		this.args = args;
		validate();
		initializeFields();
	}
	
	public abstract Coordinates execute(Robot r);
	
	protected abstract boolean validate();
	protected abstract boolean initializeFields();
	

	
}
