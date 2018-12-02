package com.klezovich.robot.commands;

import com.klezovich.robot.domain.Robot;

public class RobotSleepCommand implements RobotCommand{

	Robot robot;

	public RobotSleepCommand( Robot robot ) {
		this.robot = robot;
	}
	
	public void execute() {
		
	}
}
