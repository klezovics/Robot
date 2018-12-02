package com.klezovich.robot.domain.command;

import java.util.HashMap;
import java.util.Map;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Robot;

public class ForwardCommand extends Command {

	private static final String name="FORWARD";
	
	private Integer distance;
	
	
	public ForwardCommand( String[] args ) {
		super(args);
	}
	
	@Override
	public Coordinates execute(Robot r) {
	
	  return r.moveForward( distance );	
		
	}

	@Override
	protected boolean validate() {
		
		
		
		return true;
	}
	
	@Override
	protected boolean initializeFields() {
		
		System.out.println("Printing command arguments ... " + args.length );
		for( String arg : args ){
			System.out.println("Arg:" + arg);
		}
		System.out.println("Done printing command arguments ... ");
		distance = Integer.valueOf(args[0]);
		
		return true;
	}
	
}
