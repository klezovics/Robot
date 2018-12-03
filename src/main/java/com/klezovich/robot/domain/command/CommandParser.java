package com.klezovich.robot.domain.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.klezovich.robot.domain.ScriptLine;
import com.klezovich.robot.domain.command.exception.CommandParseException;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class CommandParser {

	private String scriptText;
	private static final String commandSeparator = "[\\r\\n]+";
	private static final String lineCommentSymbols = "//";
	private static final String commandArgSep = "(\\s)+";
	private static final String robotCommandPackage = "com.klezovich.robot.command";

	public CommandParser(String scriptText) {
		this.scriptText = scriptText;
	}

	public List<Command> parseScript() {

		List<ScriptLine> lines = null;
		
		lines = ScriptLineProcessor.splitText(getScriptText());
		lines = ScriptLineProcessor.removeComments(lines);
        lines = ScriptLineProcessor.removeEmptyLines(lines);
		
        
        List<Command> commands = new ArrayList<>();
		for (int lineNum = 0; lineNum < lines.size(); lineNum++) {

			ScriptLine line = lines.get(lineNum);
			Command command = parseScriptLine(line);
			commands.add(command);
		}

		if (commands.size() == 0)
			throw new ScriptExecutionException(1,"The script contains no commands to process");
		
		if (!firstCommandIsPositionCommand(commands)) {
			throw new ScriptExecutionException(1, "First command must be a position command");
		}

		return commands;
	}

	public Command parseCommand(String cmdText) {
	
		String[] tokens = cmdText.split(commandArgSep);
		String cmdName = tokens[0];
		String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
	
		Command c = getInstance(cmdName, args);
		c.validate();
		
		return c;
	
	}

	private Boolean firstCommandIsPositionCommand( List<Command> commands ) {
		
		if( commands.size() == 0 )
			return false;
		
		Command command = commands.get(0);
		Class commandClass = command.getClass();
		if (commandClass.equals(PositionCommand.class))
			return true;
		
		return false;
	}
	
	private Command parseScriptLine( ScriptLine scriptLine ) {

		Command command = null;
		try {
			command = parseCommand(scriptLine.getText());
			command.setLineNum(scriptLine.getLineNumber());
		} catch (ScriptExecutionException e) {
			e.setLineNum(scriptLine.getLineNumber());
			throw e;
		}

		return command;
	}

	private Command getInstance(String name, String[] args) {

		System.out.println("Trying to get instance from cmd name ");

		switch (name) {
		case "POSITION":
			return new PositionCommand(args);
		case "FORWARD":
			return new ForwardCommand(args);
		case "LEFT":
			return new LeftCommand(args);
		case "RIGHT":
			return new RightCommand(args);
		case "TURNAROUND":
			return new TurnaroundCommand(args);
		case "WAIT":
			return new WaitCommand(args);
		}

		throw new CommandParseException("unknown command", name);

	}

	public String getScriptText() {
		return scriptText;
	}

	public void setScriptText(String scriptText) {
		this.scriptText = scriptText;
	}

	static public class ScriptLineProcessor {

		static List<ScriptLine> removeEmptyLines( List<ScriptLine> lines ){
			
			for( int ii=0; ii<lines.size(); ii++ ) {
				ScriptLine line = lines.get(ii);
				if( line.isEmpty() )
					lines.remove(ii);
			}
			
			return lines;
		}

		static List<ScriptLine> splitText(String text) {
			
			List<String> textLines = Arrays.asList(text.split(commandSeparator));
			List<ScriptLine> scriptLines = new ArrayList<>();
			for( int lineNum=0; lineNum<textLines.size(); lineNum++ ) {
				String lineText = textLines.get(lineNum);
				scriptLines.add( new ScriptLine(lineText, lineNum+1) );
			}
			
			return scriptLines;
		}

		static List<ScriptLine> removeComments(List<ScriptLine> lines) {

			for (int ii = 0; ii < lines.size(); ii++) {;
				ScriptLine resLine = removeComments(lines.get(ii));
				lines.set(ii, resLine);
			}

			return lines;
		}

		static ScriptLine removeComments( ScriptLine line) {
			String text = line.getText();
			String[] tokens = text.split(lineCommentSymbols);
			if( tokens.length > 0 )
				line.setText(tokens[0]);
			else 
				line.setText("");
			
			return line;
		}
	}

}
