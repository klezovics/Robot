package com.klezovich.robot.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.Orientation;
import com.klezovich.robot.Robot;
import com.klezovich.robot.command.exception.CommandValidationException;

public abstract class Command {

	private static final String tag=null;
	private static final Map<Integer,Class> argDefinitions = new HashMap<>();
	
	private String[] args;
	
	public Command( String[] args ) {
		this.args = args;
		validateArguments();
		validate();
	}
	
	public abstract boolean execute(Robot r);
	
	protected static void addArgumentDefinition( int argNum, Class ArgumentClass ) {
		
		if( ArgumentClass != String.class && ArgumentClass != Integer.class && ArgumentClass != Orientation.class )
			throw new RuntimeException("This type is not allowed");
		
		argDefinitions.put(argNum, ArgumentClass );
		
	}
	
	protected abstract boolean validate();
	
	protected boolean validateArguments() {
		
		int suppliedArgsNum = getSuppliedArgsNum();
		int cmdArgsNum = getCommandArgsNum();
		
		if( suppliedArgsNum != cmdArgsNum )
			throw formException( formArgumentListMismatchErrorMessage(suppliedArgsNum) );
		
		for( int argNum=1; argNum < getCommandArgsNum(); argNum ++ ) {
			
			//TODO Move this to a separate class
			String arg = args[argNum-1];
			Class argClass = argDefinitions.get(argNum-1);
		 
			if( argClass == String.class )
				continue;
			
			if( argClass == Integer.class ) {
				try {
				Integer intArg = Integer.valueOf(arg);
				}catch( NumberFormatException nfe ) {
					throw formException( formArgumentTypeMismatchIntErrorMessage(argNum, arg) );
				}
			}
			
			if( argClass == Orientation.class ) {
				try {
					Orientation orient = Orientation.valueOf(arg);
				}catch( Exception e ) {
					throw formException( "Invalid instance of orientation argument " + arg );
				}
			}
			
		}
		
		return true;
	}
	
	protected int getCommandArgsNum() {
		return argDefinitions.size();
	}
	
	protected int getSuppliedArgsNum() {
		return args.length;
	}
	
	
	protected String formArgumentTypeMismatchIntErrorMessage( int argNum, String argVal ) {
		return "invalid type for argument number " + argNum +" value " + argVal +".Expected type - integer";
	}
	
	protected String formArgumentListMismatchErrorMessage( int suppiedArgNum ) {
		return "this command requires exactly " + getCommandArgsNum() +" arguments. Number supplied " + suppiedArgNum; 
	}
	
	
	protected CommandValidationException formException( String errorMsg ) {
		return new CommandValidationException( "Command " + tag + ":"+ errorMsg );
	}
	
}
