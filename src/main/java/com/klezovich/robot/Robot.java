package com.klezovich.robot;

public class Robot {

	Coordinates coordinates;
	
	public Robot( Coordinates coordinates ) {
		this.coordinates = coordinates;
	}
	
	public Coordinates setPosition( Coordinates coordinates ) {
		return coordinates;
	}
	
	public Coordinates move( Integer distance, Direction direction ) {
		return coordinates;
	}
	
	public Coordinates moveForward( Integer distance ) {
		
		Orientation currentOrientation = coordinates.getOrientation();
		int x = coordinates.getX();
		int y = coordinates.getY();
		
		switch( currentOrientation ) {
		   case NORTH:
			   coordinates.setY(x - distance);
			   break;
		   case SOUTH:
			   coordinates.setY(y + distance);
			   break;
		   case WEST:
			   coordinates.setX( x - distance );
			   break;
		   case EAST:
			   coordinates.setX( x + distance );
			   break;
		}
		
		return coordinates;
	}
	
	
	public Coordinates rotate( Direction direction ) {
		return coordinates;
	}
	
	public Coordinates turnAround() {
		return coordinates;
	}

	public void sleep() {
		return;
	}
	
	
	
}
