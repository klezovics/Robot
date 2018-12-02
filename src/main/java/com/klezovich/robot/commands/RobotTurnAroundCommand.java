package com.klezovich.robot.commands;

import com.klezovich.robot.domain.Robot;

public class RobotTurnAroundCommand implements RobotCommand{

	Robot robot;

	public RobotTurnAroundCommand( Robot robot ) {
		this.robot = robot;
	}
	
	public void execute() {
		
	}
}
