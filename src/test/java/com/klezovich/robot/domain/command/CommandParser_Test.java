package com.klezovich.robot.domain.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class CommandParser_Test {

	@Test
	public void testPositionCmdMustBeFirst() {

		String script = "FORWARD 3";
		CommandParser p = new CommandParser(script);

		try {
			p.parseScript();
		} catch (ScriptExecutionException e) {
			return;
		}

		fail("First command not POSITION - not detected");
	}

	@Test
	public void testExcessiveArgDetection() {

		String script = "POSITION 1 3 EAST FORWARD 3";
		try {
		CommandParser p = new CommandParser(script);
		p.parseScript();
		}catch( ScriptExecutionException e ) {
			return;
		}
		
		fail("Excessive arguments issue not detected");

	}

	@Test
	public void testAllOK() {

		String script = "POSITION 1 3 EAST\nFORWARD 3";
		CommandParser p = new CommandParser(script);

		List<Command> cmds = p.parseScript();
		assertEquals(2, cmds.size());

	}

	@Test
	public void basicParserTest3() {

		String script = "NOCOMMAND";
		CommandParser p = new CommandParser(script);

		try {
			p.parseScript();
		} catch (ScriptExecutionException e) {
			return;
		}

		fail("Unknown command error not detected");
	}
	
	@Test
	public void testEmptyScript() {

		String script = "";
		CommandParser p = new CommandParser(script);

		try {
			p.parseScript();
		} catch (ScriptExecutionException e) {
			return;
		}

		fail("Empty script did not trigger exception");
	}
	
	@Test
	public void testScriptWithEmptyLines() {

		String script = "\n\nPOSITION 1 3 EAST\n\nFORWARD 3";
		CommandParser p = new CommandParser(script);

		
		List<Command> cmds = p.parseScript();
		assertEquals(2, cmds.size() );
		
	}
	
	
	@Test
	public void testIndividualCommandParsing() {
		
		String cmdText = null;
		Command c = null;
		
		CommandParser p = new CommandParser("");
		
	    cmdText = "FORWARD 3";
	    c = p.parseCommand(cmdText);
	    assertEquals(c.getClass(), ForwardCommand.class );
	    
	    cmdText = "FORWARD -3";
	    try {
	      c = p.parseCommand(cmdText);
	      fail("Failed to catch issue with negative argument");
	    }catch( ScriptExecutionException e ){}
	    
	    cmdText = "POSITION 1 1 NA";
	    try {
	      c = p.parseCommand(cmdText);
	      fail("Failed to catch issue with bad orientation");
	    }catch( ScriptExecutionException e ){}
	    
		
	}
}
