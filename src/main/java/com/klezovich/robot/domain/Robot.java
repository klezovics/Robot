package com.klezovich.robot.domain;

import com.klezovich.robot.domain.command.exception.MoveForwardCmdExecutionException;

public class Robot {

	Coordinates coordinates;
	Integer maxX=5;
	Integer maxY=5;

	
	public Robot() {
		
	}
	
	public Robot(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Coordinates moveForward(Integer distance) {

		if( null == getCoordinates() )
			throw new RuntimeException("Attempting to move unitiliazed robot");
		
		Orientation currentOrientation = coordinates.getOrientation();
		int x = coordinates.getX();
		int y = coordinates.getY();

		Coordinates newCoordinates = new Coordinates( coordinates );
		
		System.out.println("Moving distance " + distance + "or:" + currentOrientation );
		
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

		validateCoordiantes(newCoordinates);
		
		coordinates=newCoordinates;
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

	public Coordinates setCoordinates(Coordinates coordinates) {
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

	private boolean validateCoordiantes( Coordinates coordinates ) {
		
		boolean valid = true;
		
		int x = coordinates.getX();
		int y = coordinates.getY();
		
		if( x < 0 || x > getMaxX() )
			valid = false;
		
		if( y < 0 || y > getMaxY() )
			valid = false;
		
		if( !valid )
		   throw new RobotException("Invalid move which leads to out of range coordinates " + coordinates);
			
		return true;
	}
	
	class RobotException extends RuntimeException{
			
		RobotException( String error ){
			super(error);
		}
	}
}
