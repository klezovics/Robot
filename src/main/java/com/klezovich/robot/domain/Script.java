package com.klezovich.robot.domain;

public class Script {

	private String text;

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
		return text;
	}
	
}
