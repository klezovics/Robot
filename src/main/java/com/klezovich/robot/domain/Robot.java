package com.klezovich.robot.domain;

public class Robot {

	Coordinates coordinates;
	Integer maxX = 5;
	Integer maxY = 5;

	public Robot() {
		
	}
	
	public Robot(Coordinates coordinates) {
		
		boolean valid = validateCoordinates(coordinates);
		if (!valid)
			throw new RobotException("Invalid move which leads to out of range coordinates " + coordinates);

		this.coordinates = coordinates;

	}

	public Robot(Coordinates coordinates, Integer maxX, Integer maxY) {
		this.maxX = maxX;
		this.maxY = maxY;

		boolean valid = validateCoordinates(coordinates);
		if (!valid)
			throw new RobotException("Invalid move which leads to out of range coordinates " + coordinates);

		this.coordinates = coordinates;
	}

	public Coordinates moveForward(Integer distance) {

		if (null == getCoordinates())
			throw new RuntimeException("Attempting to move unitiliazed robot");

		Orientation currentOrientation = coordinates.getOrientation();
		int x = coordinates.getX();
		int y = coordinates.getY();

		Coordinates newCoordinates = new Coordinates(coordinates);

		//System.out.println("Moving distance " + distance + "or:" + currentOrientation);

		switch (currentOrientation) {
		case NORTH:
			newCoordinates.setY(y - distance);
			break;
		case SOUTH:
			newCoordinates.setY(y + distance);
			break;
		case WEST:
			newCoordinates.setX(x - distance);
			break;
		case EAST:
			newCoordinates.setX(x + distance);
			break;
		}

		validateCoordinates(newCoordinates);

		coordinates = newCoordinates;
		return coordinates;
	}

	public Coordinates rotate(Direction direction) {

		if (null == getCoordinates())
			throw new RuntimeException("Attempting to move unitiliazed robot");

		Orientation robotOrientation = coordinates.getOrientation();
		robotOrientation = robotOrientation.rotate(direction);
		coordinates.setOrientation(robotOrientation);

		return coordinates;

	}

	public Coordinates setCoordinates(Coordinates coordinates) {

		boolean valid = validateCoordinates(coordinates);

		if (!valid)
			throw new RobotException("Invalid move which leads to out of range coordinates " + coordinates);

		this.coordinates = coordinates;
		return coordinates;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public Integer getMaxX() {
		return maxX;
	}

	public void setMaxX(Integer maxX) {
		this.maxX = maxX;
	}

	public Integer getMaxY() {
		return maxY;
	}

	public void setMaxY(Integer maxY) {
		this.maxY = maxY;
	}

	private boolean validateCoordinates(Coordinates c) {
		boolean valid = true;

		int x = c.getX();
		int y = c.getY();

		if (x < 0 || x > getMaxX())
			valid = false;

		if (y < 0 || y > getMaxY())
			valid = false;

		return valid;
	}

	public static class RobotException extends RuntimeException {

		RobotException(String error) {
			super(error);
		}
	}
}
