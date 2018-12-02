package com.klezovich.robot.commands;

import com.klezovich.robot.domain.Robot;

public class RobotPositionCommand implements RobotCommand{

	Robot robot;

	public RobotPositionCommand( Robot robot ) {
		this.robot = robot;
	}
	
	public void execute() {
		
	}
}
