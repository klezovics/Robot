package com.klezovich.robot.service.implementation;

import static com.klezovich.robot.domain.Orientation.EAST;
import static com.klezovich.robot.domain.Orientation.NORTH;
import static com.klezovich.robot.domain.Orientation.SOUTH;
import static com.klezovich.robot.domain.Orientation.WEST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Orientation;
import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;
import com.klezovich.robot.service.ScriptExecutionService;

@RunWith(SpringRunner.class)
public class ScriptExecutionService_Test {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public ScriptExecutionService employeeService() {
			return new ScriptExecutionServiceImpl();
		}
	}

	@Autowired
	ScriptExecutionService ses;

	private void doAutomatedTest(int maxX, int maxY) {

		Random r = new Random();

		int x1 = r.nextInt(maxX);
		int y1 = r.nextInt(maxY);

		int x2 = r.nextInt(maxX);
		int y2 = r.nextInt(maxY);

		int o1 = r.nextInt(4);
		int o2 = r.nextInt(4);
		Orientation[] or = Orientation.values();

		Coordinates start = new Coordinates(x1, y1, or[o1]);
		Coordinates finish = new Coordinates(x2, y2, or[o2]);
		Script s = ScriptGenerator.generateScript(start, finish);
        s.setMaxX(maxX);
		s.setMaxY(maxY);
		
		Coordinates result = ses.executeScript(s);
		assertEquals(finish, result);

	}

	@Test 
	public void automatedTestSequence() {
		for( int ii=0; ii<1000; ii++ ) {
		   
		   Random r = new Random();
		   int maxX = 1 + r.nextInt(50);
		   int maxY = 1 + r.nextInt(50);
		   doAutomatedTest(maxX, maxY);
		}
	}

	@Test
	public void easyAutomatedTest() {

		Coordinates start = new Coordinates(0, 0, NORTH);
		Coordinates finish = new Coordinates(4, 4, WEST);
		Script s = ScriptGenerator.generateScript(start, finish);

		// System.out.println(s);

		Coordinates result = ses.executeScript(s);
		assertEquals(finish, result);

	}

	@Test
	public void emptyInputTests() {

		Script s = new Script("", 5, 5);
		try {
			ses.executeScript(s);
			fail("Empty script");
		} catch (ScriptExecutionException e) {
		}

		s = new Script("//", 5, 5);
		try {
			ses.executeScript(s);
			fail("Emptpy script");
		} catch (ScriptExecutionException e) {
		}

		s = new Script("//abc", 5, 5);
		try {
			ses.executeScript(s);
			fail("Empty script");
		} catch (ScriptExecutionException e) {
		}

		s = new Script("\n", 5, 5);
		try {
			ses.executeScript(s);
			fail("Empty script");
		} catch (ScriptExecutionException e) {
		}

		s = new Script("\n\n", 5, 5);
		try {
			ses.executeScript(s);
			fail("Empty script");
		} catch (ScriptExecutionException e) {
		}

	}

	@Test
	public void unknownCommandTests() {

		Script s = new Script("FAKECMD", 5, 5);
		try {
			ses.executeScript(s);
			fail("Wrong command");
		} catch (ScriptExecutionException e) {
		}

		s = new Script("FAKECMD //", 5, 5);
		try {
			ses.executeScript(s);
			fail("Wrong command");
		} catch (ScriptExecutionException e) {
		}

		s = new Script("FAKECMD 1 2 3 4", 5, 5);
		try {
			ses.executeScript(s);
			fail("Wrong command");
		} catch (ScriptExecutionException e) {
		}

		s = new Script("POSITION 1 3 EAST\n\n\nFAKECMD 1 2 3 4", 5, 5);
		try {
			ses.executeScript(s);
			fail("Wrong command");
		} catch (ScriptExecutionException e) {
		}
	}

	@Test
	public void validMovementTests() {

		Script s = new Script("POSITION 0 0 SOUTH\nFORWARD 1\n", 5, 5);
		Coordinates c = ses.executeScript(s);
		assertEquals(new Coordinates(0, 1, SOUTH), c);

		s = new Script("POSITION 0 0 SOUTH\nTURNAROUND", 5, 5);
		c = ses.executeScript(s);
		assertEquals(new Coordinates(0, 0, NORTH), c);

		s = new Script("POSITION 0 0 SOUTH\nLEFT", 5, 5);
		c = ses.executeScript(s);
		assertEquals(new Coordinates(0, 0, EAST), c);

		s = new Script("POSITION 0 0 SOUTH\nRIGHT", 5, 5);
		c = ses.executeScript(s);
		assertEquals(new Coordinates(0, 0, WEST), c);

		s = new Script("POSITION 0 0 SOUTH\nWAIT", 5, 5);
		c = ses.executeScript(s);
		assertEquals(new Coordinates(0, 0, SOUTH), c);

		StringBuilder sb = new StringBuilder();
		sb.append("POSITION 0 0 SOUTH\n");
		sb.append("FORWARD 4\n");
		sb.append("POSITION 0 0 SOUTH\n");
		sb.append("FORWARD 4\n");
		sb.append("LEFT\n");
		sb.append("WAIT\n");
		sb.append("TURNAROUND\n");
		sb.append("TURNAROUND\n");
		sb.append("FORWARD 4\n");
		sb.append("LEFT\n");
		sb.append("FORWARD 4\n");
		sb.append("LEFT\n");
		sb.append("FORWARD 4\n");
		sb.append("RIGHT");
		s = new Script(sb.toString(), 5, 5);
		c = ses.executeScript(s);
		assertEquals(new Coordinates(0, 0, NORTH), c);

	}

	@Test
	public void robotTriesToDriveOutOfArenaTests() {

		Script s = new Script("POSITION 0 0 SOUTH\nFORWARD 1\n", 1, 1);
		Coordinates c = null;
		try {
			c = ses.executeScript(s);
			fail("Robot is able to leave the arena");
		} catch (ScriptExecutionException e) {
		}

		s = new Script("POSITION 0 0 SOUTH\nFORWARD 50\n", 30, 30);
		try {
			c = ses.executeScript(s);
			fail("Robot is able to leave the arena");
		} catch (ScriptExecutionException e) {
		}

		s = new Script("POSITION 0 0 SOUTH\nFORWARD 50\n", 30, 51);
		try {
			c = ses.executeScript(s);
		} catch (ScriptExecutionException e) {
			fail("Robot is actually in the arena");
		}

	}

	@Test
	public void basicTest() {

		Script s = new Script("WAIT", 5, 5);
		try {
			ses.executeScript(s);
			fail("First command must be position command");
		} catch (ScriptExecutionException e) {
		}

	}

}
