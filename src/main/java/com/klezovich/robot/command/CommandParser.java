package com.klezovich.robot.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.klezovich.robot.command.exception.CommandParseException;

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
		lines = removeComments(lines);

		System.out.println("Total lines:" + lines.size());
		for (int lineNum = 1; lineNum < lines.size(); lineNum++) {
			System.out.println("PARSING LINE  NUMBER:" + lineNum);
			Command command = parseCommandFromTxt(lines.get(lineNum));

			if (lineNum == 1) {
				Class commandClass = command.getClass();
				if (!commandClass.equals(PositionCommand.class))
					throw new CommandParseException("First command must be a position command");
			}

			commands.add(command);
		}

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
