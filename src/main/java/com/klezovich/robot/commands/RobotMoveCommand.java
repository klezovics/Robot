package com.klezovich.robot.commands;

import com.klezovich.robot.domain.Robot;

public class RobotMoveCommand implements RobotCommand{

	Robot robot;

	public RobotMoveCommand( Robot robot ) {
		this.robot = robot;
	}
	
	public void execute() {
		
	}
}
