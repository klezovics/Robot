package com.klezovich.robot.domain;

public class Robot {

	private Coordinates coordinates;
	private Integer maxX = 5;
	private Integer maxY = 5;

	
	public Robot( int maxX, int maxY ) {
		this.maxX =maxX;
		this.maxY=maxY;
	}
	
	public Robot(Coordinates coordinates) {
		
		validateCoordinates(coordinates);
		this.coordinates = coordinates;

	}

	public Robot(Coordinates coordinates, Integer maxX, Integer maxY) {
		this.maxX = maxX;
		this.maxY = maxY;

	    validateCoordinates(coordinates);
		
		this.coordinates = coordinates;
	}

	public Coordinates setCoordinates(Coordinates coordinates) {
	
		validateCoordinates(coordinates);
	
		this.coordinates = coordinates;
		return coordinates;
	}

	public Coordinates moveForward(Integer distance) {

		validateCoordinates(this.coordinates);
		
		Orientation currentOrientation = coordinates.getOrientation();
		int x = coordinates.getX();
		int y = coordinates.getY();

		Coordinates newCoordinates = new Coordinates(coordinates);


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

		validateCoordinates(this.coordinates);

		Orientation robotOrientation = coordinates.getOrientation();
		robotOrientation = robotOrientation.rotate(direction);
		coordinates.setOrientation(robotOrientation);

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

	
	private boolean validateCoordinates(Coordinates coordinates) {
		
		if( coordinates == null )
			throw new RobotControlException("uninitialized coordinates");
		
		boolean valid = true;

		int x = coordinates.getX();
		int y = coordinates.getY();

		if (x < 0 || x >= getMaxX())
			valid = false;

		if (y < 0 || y >= getMaxY())
			valid = false;
		
		if (!valid)
			throw new RobotControlException("invalid move which leads to out of range coordinates " + coordinates);

		return valid;
	}

	public static class RobotControlException extends RuntimeException {

		RobotControlException(String error) {
			super(error);
		}
	}
}
