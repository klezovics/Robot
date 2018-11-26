package com.klezovich.robot;

public class Coordinates {

	private Integer x;
    private  Integer y;
	private Orientation orienation;
    
	public Coordinates( Integer x, Integer y, Orientation orientation ) {
		this.x=x;
		this.y=y;
		this.orienation = orientation;
	}
	
    
	public Orientation getOrienation() {
		return orienation;
	}
	public void setOrienation(Orientation orienation) {
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
