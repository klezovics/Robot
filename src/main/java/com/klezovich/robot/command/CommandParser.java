package com.klezovich.robot.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {

	private String scriptText;
	private static final String commandSeparator = "\n";
	private static final String lineCommentSymbols = "//";
	private static final String commandArgSep = "\\s+";
	
	public CommandParser( String scriptText ) {
		this.scriptText = scriptText;
	}
	
	public List<Command> parseScript(){
		List<Command> commands = new ArrayList<>();
		
		List<String> lines = getScriptLines(scriptText);
		lines = removeComments(lines);
		
		return commands;
	}
	
	private List<String> getScriptLines( String scriptText ){
	
		List<String> lines = Arrays.asList( scriptText.split(commandSeparator) );
		
		return lines;
	}
	
	private List<String> removeComments( List<String> lines ){
		
		for( int ii=0; ii<lines.size(); ii++ ) {
		   String str = lines.get(ii);
		   String[] tokens = str.split(lineCommentSymbols);
		   if( !tokens[0].equals("") )
		      lines.set(ii, tokens[0] );
		}
		
		return lines;
	}

	private Command parseCommandFromTxt( String text ) {
		
		String[] tokens = text.split( commandArgSep );
		String cmdName = tokens[0];
		String[] args = Arrays.copyOfRange(tokens,1, tokens.length);
		
		return null;
	}
	
	private Command getInstanceFromCmdName( String name ) {
		return null;
	}
	
	public String getScriptText() {
		return scriptText;
	}

	public void setScriptText(String scriptText) {
		this.scriptText = scriptText;
	}
	
	
}
