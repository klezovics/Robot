package com.klezovich.robot.command;

import java.util.Map;

import com.klezovich.robot.Orientation;
import com.klezovich.robot.command.exception.CommandValidationException;

public class CommandArgumentValidator {

	private Map<String, Class> argDefinitions;
	private String[] args;
	
	String cmdTag;

	CommandArgumentValidator(Map<Integer, Class> argDefintions, String[] args ) {
		this.argDefinitions = argDefinitions;
		this.args = args;
	}

	public boolean validate() {

		validateArgListSize();
		validateArgListTypes();

		return true;
	}

	protected boolean validateArgListTypes() {

		for (int argNum = 1; argNum < getCommandArgsNum(); argNum++) {

			// TODO Move this to a separate class
			String arg = args[argNum - 1];
			Class argClass = argDefinitions.get(argNum - 1);

			if (argClass == String.class)
				continue;

			if (argClass == Integer.class) {
				try {
					Integer intArg = Integer.valueOf(arg);
				} catch (NumberFormatException nfe) {
					throw formException(formArgumentTypeMismatchIntErrorMessage(argNum, arg));
				}
			}

			if (argClass == Orientation.class) {
				try {
					Orientation orient = Orientation.valueOf(arg);
				} catch (Exception e) {
					throw formException("Invalid instance of orientation argument " + arg);
				}
			}

		}

		return true;
	}

	protected boolean validateArgListSize() {

		int suppliedArgsNum = getSuppliedArgsNum();
		int cmdArgsNum = getCommandArgsNum();

		if (suppliedArgsNum != cmdArgsNum)
			throw formException(formArgumentListMismatchErrorMessage(suppliedArgsNum));

		return true;

	}

	protected int getCommandArgsNum() {
		return argDefinitions.size();
	}

	protected int getSuppliedArgsNum() {
		return args.length;
	}

	protected String formArgumentTypeMismatchIntErrorMessage(int argNum, String argVal) {
		return "invalid type for argument number " + argNum + " value " + argVal + ".Expected type - integer";
	}

	protected String formArgumentListMismatchErrorMessage(int suppiedArgNum) {
		return "this command requires exactly " + getCommandArgsNum() + " arguments. Number supplied " + suppiedArgNum;
	}

	protected CommandValidationException formException(String errorMsg) {
		return new CommandValidationException(errorMsg);
	}

}
