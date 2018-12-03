package com.klezovich.robot.domain.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public class CommandParser_Test {

	@Test
	public void basicParserTest1() {

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
	public void lineSeparatorTest() {

		String script = "POSITION 1 3 EAST FORWARD 3";
		CommandParser p = new CommandParser(script);

		List<Command> cmds = p.parseScript();
		assertEquals(1, cmds.size());

	}

	@Test
	public void basicParserTest2() {

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
}
