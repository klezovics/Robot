package com.klezovich.robot.command;

import com.klezovich.robot.Robot;

public class ForwardCommand extends Command {

	private static final String tag="FORWARD";
	private String[] args;
	private Integer distance;
	
	public ForwardCommand( String[] args ) {
		super(args);
	}
	
	protected boolean validate() {
		
		if( args.length != 1 )
		   throw formException("this command requires exactly one argument");
		
		try {
		  Integer distance = Integer.valueOf(args[0]);
		  this.distance = distance;
		}catch( NumberFormatException nfe ) {
		  throw formException("the first argument to this command must be an integer. Current value:" + args[0] );
		}
		
		
		
		return true;
	}
	
	public boolean execute(Robot r) {
	  return true;	
	}
	
}
