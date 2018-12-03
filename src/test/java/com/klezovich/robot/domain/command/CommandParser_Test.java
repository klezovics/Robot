package com.klezovich.robot.domain.command;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class CommandParser_Test {

	
	@Test
	public void basicParserTest1() {
		
		String script = "FORWARD 3";
		CommandParser p = new CommandParser(script);
		
		try {
			p.parseScript();
		}catch( ScriptExecutionException e) {
			return ;
		}
		
		fail("First command not POSITION - not detected");
		
	}
}
