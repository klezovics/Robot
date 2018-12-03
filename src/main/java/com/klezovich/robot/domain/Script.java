package com.klezovich.robot.domain;

public class Script {

	private String text;
	Integer maxX;
	Integer maxY;

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

	public Script() {
		
	}
	
	public Script( String text ) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Script [text=" + text + ", maxX=" + maxX + ", maxY=" + maxY + "]";
	}
	
	
}
