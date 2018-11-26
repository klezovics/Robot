package com.klezovich.robot;

public class Coordinates {

	private Integer x;
    private Integer y;
	private Direction orienation;
    
	public Coordinates( Integer x, Integer y, Direction orientation ) {
		this.x=x;
		this.y=y;
		this.orienation = orientation;
	}
	
    
	public Direction getOrienation() {
		return orienation;
	}
	public void setOrienation(Direction orienation) {
		this.orienation = orienation;
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
