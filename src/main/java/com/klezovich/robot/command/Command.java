package com.klezovich.robot.command;

import com.klezovich.robot.Robot;

public abstract class Command {

	private String tag;
	private String[] args;
	
	public Command( String[] args ) {
		this.args = args;
		validate();
	}
	
	protected abstract boolean validate();
	public abstract boolean execute(Robot r);
	
}
