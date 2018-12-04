package com.klezovich.robot.domain;

public class Script {

	private String text;
	private Integer maxX;
	private Integer maxY;

	
	public Script() {
		
	}
	
	public Script(String text, Integer maxX, Integer maxY) {
		this.text = text;
		this.maxX = maxX;
		this.maxY = maxY;
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
