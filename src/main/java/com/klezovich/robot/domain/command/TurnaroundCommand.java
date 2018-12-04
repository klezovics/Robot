package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Direction;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.command.exception.ArgsForZeroArgCommandException;
import com.klezovich.robot.domain.command.exception.CommandValidationException;

public class TurnaroundCommand extends Command {
		

	public TurnaroundCommand( String[] args, Integer lineNum ) {
		super(args, lineNum);
		this.name="TURNAROUND";
		validate();
	}
	
	
	@Override
	public Coordinates execute(Robot r) {
	
	  Direction rotationDirection = Direction.getRandomLeftOrRight();
	
	  r.rotate( rotationDirection );
	  r.rotate( rotationDirection );
	
	  return r.getCoordinates();
	  
	}

	@Override
	protected boolean validate() {
		if( 0 != getArgNum() )
			throw new ArgsForZeroArgCommandException(this);
		
		return true;
	}
	
}
