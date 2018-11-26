package com.klezovich.robot;

public class Coordinates {

	private Integer x;
	private Integer y;
	private Orientation orientation;

	public Coordinates(Integer x, Integer y, Orientation orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
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
