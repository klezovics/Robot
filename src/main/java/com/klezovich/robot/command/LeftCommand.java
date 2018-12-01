package com.klezovich.robot.command;

import com.klezovich.robot.Direction;
import com.klezovich.robot.Robot;

public class LeftCommand extends Command {

	private static final String tag="LEFT";
	private String[] args;
	
	public LeftCommand( String[] args ) {
		super(args);
	}
	
	protected boolean validate() {
		if( !argListEmpty() )
			throw formArgsForNoArgCmdException();
		return true;
	}
	
	public boolean execute(Robot r) {
	  
	  r.rotate( Direction.LEFT );
	  return true;	
	}
	
}
