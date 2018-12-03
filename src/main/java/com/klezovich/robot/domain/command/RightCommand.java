package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Direction;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.command.exception.ArgsForZeroArgCommandException;

public class RightCommand extends Command {

	
	public RightCommand(String[] args) {
		super(args);
		this.name ="RIGHT";
		validate();
	}
	
	@Override
	public Coordinates execute(Robot r) {
		
		return r.rotate(Direction.RIGHT);
	}

	@Override
	protected boolean validate() {
		
		if( 0 != getArgNum() )
			throw new ArgsForZeroArgCommandException(this);
		
		return true;
	}

	@Override
	protected boolean initializeFields() {
		return true;
	}

}
