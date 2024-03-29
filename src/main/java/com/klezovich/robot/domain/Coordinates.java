package com.klezovich.robot.domain;

public class Coordinates {

	private Integer x;
	private Integer y;
	private Orientation orientation;

	public Coordinates(Integer x, Integer y, Orientation orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	
	public Coordinates( Coordinates otherCoordiantes ) {
		this.x = otherCoordiantes.getX();
		this.y = otherCoordiantes.getY();
		this.orientation = otherCoordiantes.getOrientation();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (orientation != other.orientation)
			return false;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + ", orientation=" + orientation + "]";
	}

	
	
}
