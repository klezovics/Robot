package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.command.exception.ArgsForZeroArgCommandException;

public class WaitCommand extends Command {
	
	public WaitCommand( String[] args) {
		super(args);
		this.name="WAIT";
		validate();
	}
	
	@Override
	public Coordinates execute(Robot r) {
        return  r.getCoordinates();
	}

	@Override
	protected boolean validate() {
		
		if( 0 != getArgNum() )
			throw new ArgsForZeroArgCommandException(this);
		
		return true;
	}

	
}
