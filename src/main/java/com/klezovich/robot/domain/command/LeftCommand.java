package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Direction;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.command.exception.ArgsForZeroArgCommandException;

public class LeftCommand extends Command {
	
	private String name="LEFT";
	
	public LeftCommand( String[] args, Integer lineNum) {
		super(args, lineNum);
		this.name="LEFT";
		validate();
	}	
	
	
	@Override
	public Coordinates execute(Robot r) {
	
	  return r.rotate( Direction.LEFT );
	  	
	}

	@Override
	protected boolean validate() {
		
		if( 0 != getArgNum() )
			throw new ArgsForZeroArgCommandException(this);
		
		return true;
	}
	
	
}
