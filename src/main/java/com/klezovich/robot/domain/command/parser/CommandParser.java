package com.klezovich.robot.domain.command.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.ScriptLine;
import com.klezovich.robot.domain.command.Command;
import com.klezovich.robot.domain.command.ForwardCommand;
import com.klezovich.robot.domain.command.LeftCommand;
import com.klezovich.robot.domain.command.PositionCommand;
import com.klezovich.robot.domain.command.RightCommand;
import com.klezovich.robot.domain.command.TurnaroundCommand;
import com.klezovich.robot.domain.command.WaitCommand;
import com.klezovich.robot.domain.command.exception.CommandParseException;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class CommandParser {

	private static final String commandSeparator = "[\\r\\n]+";
	private static final String lineCommentSymbols = "//";
	private static final String commandArgSep = "(\\s)+";

	

	public static List<Command> parseScript( Script script ) {

		List<ScriptLine> scriptLines = preProcessScript( script );

		List<Command> commands = new ArrayList<>();
		for (ScriptLine line : scriptLines ) {

			Command command = null;
			try {
				command = parseCommand(line);
			} catch (CommandParseException e) {
				e.setLineNum(line.getLineNumber());
				throw e;
			}

			command.setLineNum(line.getLineNumber());
			commands.add(command);
		}

		if (commands.size() == 0)
			throw new ScriptExecutionException(1, "the script contains no commands to process");

		if (!firstCommandIsPositionCommand(commands)) {
			throw new ScriptExecutionException(1, "first command must be a position command");
		}

		return commands;
	}

	public static Command parseCommand(ScriptLine cmdLine) {

		String cmdText = cmdLine.getText(); 
		String[] tokens = cmdText.split(commandArgSep);
		String cmdName = tokens[0];
		String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

		Command c = buildCommandObject(cmdName, args, cmdLine.getLineNumber() );

		return c;

	}

	private static Boolean firstCommandIsPositionCommand(List<Command> commands) {

		if (commands.size() == 0)
			return false;

		Command command = commands.get(0);
		Class<? extends Command> commandClass = command.getClass();
		if (commandClass.equals(PositionCommand.class))
			return true;

		return false;
	}

	private static List<ScriptLine> preProcessScript( Script script ) {
		List<ScriptLine> lines = ScriptLineProcessor.splitText(script.getText());
		lines = ScriptLineProcessor.removeComments(lines);
		lines = ScriptLineProcessor.removeEmptyLines(lines);
		return lines;
	}

	private static Command buildCommandObject(String name, String[] args, Integer lineNum ) {

		switch (name) {
		case "POSITION":
			return new PositionCommand(args, lineNum);
		case "FORWARD":
			return new ForwardCommand(args, lineNum);
		case "LEFT":
			return new LeftCommand(args, lineNum);
		case "RIGHT":
			return new RightCommand(args, lineNum);
		case "TURNAROUND":
			return new TurnaroundCommand(args,lineNum);
		case "WAIT":
			return new WaitCommand(args, lineNum);
		}

		throw new CommandParseException("unknown command", name);

	}

	static public class ScriptLineProcessor {

		public static List<ScriptLine> removeEmptyLines(List<ScriptLine> lines) {

			for (int ii = 0; ii < lines.size(); ii++) {
				ScriptLine line = lines.get(ii);
				if (line.isEmpty())
					lines.remove(ii);
			}

			return lines;
		}

		public static List<ScriptLine> splitText(String text) {

			List<String> textLines = Arrays.asList(text.split(commandSeparator));
			List<ScriptLine> scriptLines = new ArrayList<>();
			for (int lineNum = 0; lineNum < textLines.size(); lineNum++) {

				String lineText = textLines.get(lineNum);
				scriptLines.add(new ScriptLine(lineText, lineNum + 1));
			}

			return scriptLines;
		}

		public static List<ScriptLine> removeComments(List<ScriptLine> lines) {

			for (int ii = 0; ii < lines.size(); ii++) {

				ScriptLine resLine = removeComments(lines.get(ii));
				lines.set(ii, resLine);
			}

			return lines;
		}

		public static ScriptLine removeComments(ScriptLine line) {
			String text = line.getText();
			String[] tokens = text.split(lineCommentSymbols);
			if (tokens.length > 0)
				line.setText(tokens[0]);
			else
				line.setText("");

			return line;
		}
	}

}
