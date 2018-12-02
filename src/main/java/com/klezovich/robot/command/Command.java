package com.klezovich.robot.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.Orientation;
import com.klezovich.robot.Robot;

public abstract class Command {

	private static final String name=null;
	private static final Map<Integer,Class> argDefinitions = new HashMap<>();
	
	private String[] args;
	
	public Command( String[] args ) {
		this.args = args;
		validateArgumentListSizeAndTypes();
		validateArguments();
		initializeFields();
	}
	
	public abstract boolean execute(Robot r);
	
	protected abstract boolean validateArguments();
	protected abstract boolean initializeFields();
	
	protected boolean validateArgumentListSizeAndTypes() {
		CommandArgumentValidator cmdArgVal = new CommandArgumentValidator(argDefinitions, args);
		cmdArgVal.validate();
		return true;
	}

	protected static void addArgumentDefinition( int argNum, Class ArgumentClass ) {
		
		if( ArgumentClass != String.class && ArgumentClass != Integer.class && ArgumentClass != Orientation.class )
			throw new RuntimeException("This type is not allowed");
		
		argDefinitions.put(argNum, ArgumentClass );
		
	}

	public static String getCommandName() {
		return name;
	}

	
}
