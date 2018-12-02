package com.klezovich.robot.commands;

import com.klezovich.robot.domain.Robot;

public class RobotRotateCommand implements RobotCommand{

	Robot robot;

	public RobotRotateCommand( Robot robot ) {
		this.robot = robot;
	}
	
	public void execute() {
		
	}
}
