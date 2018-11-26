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
