package com.klezovich.robot;

import static com.klezovich.robot.Orientation.*;
import static com.klezovich.robot.Direction.*;

public class Robot {

	Coordinates coordinates;

	public Robot() {
		
	}
	
	public Robot(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Coordinates setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
		return coordinates;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public Coordinates moveForward(Integer distance) {

		if( null == getCoordinates() )
			throw new RuntimeException("Attempting to move unitiliazed robot");
		
		Orientation currentOrientation = coordinates.getOrientation();
		int x = coordinates.getX();
		int y = coordinates.getY();

		switch (currentOrientation) {
		case NORTH:
			coordinates.setY(y - distance);
			break;
		case SOUTH:
			coordinates.setY(y + distance);
			break;
		case WEST:
			coordinates.setX(x - distance);
			break;
		case EAST:
			coordinates.setX(x + distance);
			break;
		}

		return coordinates;
	}

	public Coordinates rotate(Direction direction) {

		if( null == getCoordinates() )
			throw new RuntimeException("Attempting to move unitiliazed robot");
		
		Orientation robotOrientation = coordinates.getOrientation();
        robotOrientation = robotOrientation.rotate(direction);
	    coordinates.setOrientation(robotOrientation);

		return coordinates;

	}

}
