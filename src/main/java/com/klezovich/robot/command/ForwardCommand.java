package com.klezovich.robot.command;

import com.klezovich.robot.Robot;

public class ForwardCommand extends Command {

	private static final String tag="FORWARD";
	private String[] args;
	private Integer distance;
	
	static {
		addArgumentDefinition(0,Integer.class);
	}
	
	public ForwardCommand( String[] args ) {
		super(args);
	}
	
	@Override
	protected boolean validate() {
		
		return true;
	}
	
	@Override
	public boolean execute(Robot r) {
	  
	  r.moveForward( Integer.valueOf( args[0]) );	
		
	  return true;	
	}
	
}
