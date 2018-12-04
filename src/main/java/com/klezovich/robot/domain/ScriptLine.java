package com.klezovich.robot.domain;

public class ScriptLine {

	private String text;
	private Integer lineNumber;
	
	public ScriptLine(String text, Integer lineNumber) {
		super();
		this.text = text;
		this.lineNumber = lineNumber;
	}
	
	public Boolean isEmpty() {
		
		text = getText();
		
		if (text.equals("(\\s)+") || text.equals(""))
			return true;
		
		return false;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
}
