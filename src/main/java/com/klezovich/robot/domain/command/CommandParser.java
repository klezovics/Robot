package com.klezovich.robot.domain.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.klezovich.robot.domain.command.exception.CommandParseException;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class CommandParser {

	private String scriptText;
	private static final String commandSeparator = "\n";
	private static final String lineCommentSymbols = "//";
	private static final String commandArgSep = "(\\s)+";
	private static final String robotCommandPackage = "com.klezovich.robot.command";

	public CommandParser(String scriptText) {
		this.scriptText = scriptText;
	}

	public List<Command> parseScript() {

		List<Command> commands = new ArrayList<>();

		List<String> lines = getScriptLines(scriptText);
		System.out.println("==== START OF SCRIPT PARSE ===");
		System.out.println("Number of lines in script:" + lines );
		
		
		lines = removeComments(lines);
		lines = removeEmptyLines(lines);
		System.out.println("Number of lines after removal of empties and comments" + lines);
		

		System.out.println("Total lines:" + lines.size());
		for (int lineNum = 0; lineNum < lines.size(); lineNum++) {
			System.out.println("=PARSING LINE  NUMBER:" + lineNum+"=");
			
			String line = lines.get(lineNum);
			if( line.equals("(\\s)+") || line.equals("") ){
				//Skipping empty lines
				System.out.println("Line number " + lineNum + "is empty");
				continue;
			}
			
			Command command = null;
			try {
			   command = parseCommandFromTxt(lines.get(lineNum));
			} catch( ScriptExecutionException e ) {
				e.setLineNum(lineNum+1);
				throw e;
			}
			
			if (lineNum == 0) {
				Class commandClass = command.getClass();
				if (!commandClass.equals(PositionCommand.class)) {
					throw new ScriptExecutionException(1,"First command must be a position command");
				}
			}
			

			commands.add(command);
		}

		System.out.println("==== END OF SCRIPT PARSE ===");
		return commands;
	}

	private List<String> getScriptLines(String scriptText) {

		List<String> lines = Arrays.asList(scriptText.split(commandSeparator));

		return lines;
	}

	private List<String> removeComments(List<String> lines) {

		for (int ii = 0; ii < lines.size(); ii++) {
			String str = lines.get(ii);
			String[] tokens = str.split(lineCommentSymbols);
			if (!tokens[0].equals(""))
				lines.set(ii, tokens[0]);
		}

		return lines;
	}
	
	private List<String> removeEmptyLines(List<String> lines) {

		for (int ii = 0; ii < lines.size(); ii++) {
			String line = lines.get(ii);
			if( line.equals("(\\s)+") || line.equals("") ){
				lines.remove(ii);
			}
		}

		return lines;
	}

	private Command parseCommandFromTxt(String text) {

		System.out.println("Parsing command form text: " + text);
		String[] tokens = text.split(commandArgSep);
		System.out.println("Token num" + tokens.length);
		String cmdName = tokens[0];
		String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

		return getInstanceFromCmdName(cmdName, args);

	}

	private Command getInstanceFromCmdName(String name, String[] args) {

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
		
		throw new CommandParseException("Unknown command:" + name);

	}

	public String getScriptText() {
		return scriptText;
	}

	public void setScriptText(String scriptText) {
		this.scriptText = scriptText;
	}

}
