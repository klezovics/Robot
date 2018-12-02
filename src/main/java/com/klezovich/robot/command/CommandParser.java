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
	private static final String commandArgSep = "\\s+";
	private static final String robotCommandPackage = "com.klezovich.robot.command";

	public CommandParser(String scriptText) {
		this.scriptText = scriptText;
	}

	public List<Command> parseScript() {
		List<Command> commands = new ArrayList<>();

		List<String> lines = getScriptLines(scriptText);
		lines = removeComments(lines);
		for( String line : lines ) {
		   	
		   Command command = parseCommandFromTxt(line);
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

		String[] tokens = text.split(commandArgSep);
		String cmdName = tokens[0];
		String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

		return getInstanceFromCmdName(cmdName, args);
		
	}

	private Command getInstanceFromCmdName(String name, String[] args) {

		Class baseClass = Command.class;
		Reflections reflections = new Reflections(robotCommandPackage);
		Set<Class<? extends Command>> classes = reflections.getSubTypesOf(baseClass);

		for (Class commandClass : classes) {

			String cmdName;
			try {
				cmdName = (String) commandClass.getMethod("getCommandName").invoke(null, null);

				if (name.equals(cmdName)) {
					return getInstanceFromCommandClass(commandClass, args);
				}
				
			} catch (Exception e) {
				throw new CommandParseException("Error parsing commnad:" + name);
			}

		}

		throw new CommandParseException("Unknown command:" + name);

	}

	private Command getInstanceFromCommandClass(Class commandClass, String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Command cmd = (Command) commandClass.getConstructor(String[].class).newInstance(args);

		return cmd;
	}

	public String getScriptText() {
		return scriptText;
	}

	public void setScriptText(String scriptText) {
		this.scriptText = scriptText;
	}

}
