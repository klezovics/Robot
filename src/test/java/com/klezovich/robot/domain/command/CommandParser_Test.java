package com.klezovich.robot.domain.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;
import com.klezovich.robot.domain.command.parser.CommandParser;

public class CommandParser_Test {

	@Test
	public void testPositionCmdMustBeFirst() {

		Script script = new Script("FORWARD 3", 5, 5);

		try {
			CommandParser.parseScript(script);
		} catch (ScriptExecutionException e) {
			return;
		}

		fail("First command not POSITION - not detected");
	}

	@Test
	public void testExcessiveArgDetection() {

		Script script = new Script("POSITION 1 3 EAST FORWARD 3", 5, 5);
		try {
			CommandParser.parseScript(script);
		} catch (ScriptExecutionException e) {
			return;
		}

		fail("Excessive arguments issue not detected");

	}

	@Test
	public void testAllOK() {

		Script script = new Script("POSITION 1 3 EAST\nFORWARD 3",5,5);

		List<Command> cmds = CommandParser.parseScript(script);
		assertEquals(2, cmds.size());

	}

	@Test
	public void basicParserTest3() {

		Script script =  new Script( "NOCOMMAND",5,5);

		try {
			CommandParser.parseScript(script);
		} catch (ScriptExecutionException e) {
			return;
		}

		fail("Unknown command error not detected");
	}

	@Test
	public void testEmptyScript() {


		try {
			CommandParser.parseScript( new Script("",5,5));
		} catch (ScriptExecutionException e) {
			return;
		}

		fail("Empty script did not trigger exception");
	}

	@Test
	public void testScriptWithEmptyLines() {

		Script script = new Script("\n\nPOSITION 1 3 EAST\n\nFORWARD 3",5,5);

		List<Command> cmds = CommandParser.parseScript(script);
		assertEquals(2, cmds.size());

	}

	@Test
	public void testIndividualCommandParsing() {

		String cmdText = null;
		Command c = null;

		

		cmdText = "FORWARD 3";
		c = CommandParser.parseCommand(cmdText);
		assertEquals(c.getClass(), ForwardCommand.class);

		cmdText = "FORWARD -3";
		try {
			c = CommandParser.parseCommand(cmdText);
			fail("Failed to catch issue with negative argument");
		} catch (ScriptExecutionException e) {
		}

		cmdText = "POSITION 1 1 NA";
		try {
			c = CommandParser.parseCommand(cmdText);
			fail("Failed to catch issue with bad orientation");
		} catch (ScriptExecutionException e) {
		}

	}
}
