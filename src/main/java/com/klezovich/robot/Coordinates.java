package com.klezovich.robot;

public class Coordinates {

	private Integer x;
    private Integer y;
	private Direction direction;
    
	public Coordinates( Integer x, Integer y, Direction direction ) {
		this.x=x;
		this.y=y;
		this.direction = direction;
	}
	
    
	public Direction getDirection() {
		return direction;
	}
	public void setdirection(Direction direction) {
		this.direction = direction;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
    
    
    
    
}
