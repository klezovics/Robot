package com.klezovich.robot.domain;

import static com.klezovich.robot.domain.Direction.LEFT;
import static com.klezovich.robot.domain.Direction.RIGHT;
import static com.klezovich.robot.domain.Orientation.EAST;
import static com.klezovich.robot.domain.Orientation.NORTH;
import static com.klezovich.robot.domain.Orientation.SOUTH;
import static com.klezovich.robot.domain.Orientation.WEST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.klezovich.robot.domain.Robot.RobotControlException;

public class Robot_Test {

	@Test
	public void test_Constructors() {

		Coordinates c = new Coordinates(0, 0, NORTH);
		Robot r = new Robot(c,5,5);
		assertEquals(c, r.getCoordinates());

		r = new Robot(c);
		r.setCoordinates(c);
		assertEquals(c, r.getCoordinates());
		
		
		try {
			Robot r1 = new Robot( new Coordinates(1,1,NORTH), 0, 0 );
			fail("Failed to detect out of range coordinates");
		}catch( RobotControlException e) {
			
		}

	}

	@Test
	public void test_Rotations() {

		Robot r = new Robot();
		Coordinates c = new Coordinates(0, 0, NORTH);

		r.setCoordinates(c);
		r.rotate(LEFT);
		assertEquals(new Coordinates(0, 0, WEST), r.getCoordinates());
		r.rotate(LEFT);
		assertEquals(new Coordinates(0, 0, SOUTH), r.getCoordinates());
		r.rotate(LEFT);
		assertEquals(new Coordinates(0, 0, EAST), r.getCoordinates());
		r.rotate(LEFT);
		assertEquals(new Coordinates(0, 0, NORTH), r.getCoordinates());

		r.setCoordinates(c);
		r.rotate(RIGHT);
		assertEquals(new Coordinates(0, 0, EAST), r.getCoordinates());
		r.rotate(RIGHT);
		assertEquals(new Coordinates(0, 0, SOUTH), r.getCoordinates());
		r.rotate(RIGHT);
		assertEquals(new Coordinates(0, 0, WEST), r.getCoordinates());
		r.rotate(RIGHT);
		assertEquals(new Coordinates(0, 0, NORTH), r.getCoordinates());

		r.setCoordinates(c);
		r.rotate(RIGHT);
		assertEquals(new Coordinates(0, 0, EAST), r.getCoordinates());
		r.rotate(LEFT);
		assertEquals(new Coordinates(0, 0, NORTH), r.getCoordinates());
		r.rotate(RIGHT);
		assertEquals(new Coordinates(0, 0, EAST), r.getCoordinates());
		r.rotate(RIGHT);

	}

	@Test
	public void test_LinearMovements() {

		Robot r = new Robot();

		// Moving south tests
		Coordinates c = new Coordinates(0, 0, SOUTH);

		r.setCoordinates(c);
		r.moveForward(1);
		assertEquals(r.getCoordinates(), new Coordinates(0, 1, SOUTH));

		r.moveForward(3);
		assertEquals(r.getCoordinates(), new Coordinates(0, 4, SOUTH));

		// Moving north tests
		c = new Coordinates(2, 4, NORTH);
		r.setCoordinates(c);
		r.moveForward(1);
		assertEquals(new Coordinates(2, 3, NORTH), r.getCoordinates());
		r.moveForward(3);
		assertEquals(new Coordinates(2, 0, NORTH), r.getCoordinates());

		// Moving east tests
		c = new Coordinates(1, 4, EAST);
		r.setCoordinates(c);
		r.moveForward(1);
		assertEquals(new Coordinates(2, 4, EAST), r.getCoordinates());
		r.moveForward(2);
		assertEquals(new Coordinates(4, 4, EAST), r.getCoordinates());

		// Moving west tests
		c = new Coordinates(5, 4, WEST);
		r.setCoordinates(c);
		r.moveForward(1);
		assertEquals(new Coordinates(4, 4, WEST), r.getCoordinates());
		r.moveForward(2);
		assertEquals(new Coordinates(2, 4, WEST), r.getCoordinates());

	}

	@Test
	public void test_CompountMovements1() {

		Robot r = new Robot();

		Coordinates c = new Coordinates(0, 0, SOUTH);
		r.setCoordinates(c);
		r.moveForward(4);
		assertEquals(new Coordinates(0, 4, SOUTH), r.getCoordinates());

		r.rotate(Direction.LEFT);
		assertEquals(new Coordinates(0, 4, EAST), r.getCoordinates());

		r.moveForward(2);
		assertEquals(new Coordinates(2, 4, EAST), r.getCoordinates());

		r.rotate(Direction.LEFT);
		r.rotate(Direction.LEFT);
		r.moveForward(2);
		assertEquals(new Coordinates(0, 4, WEST), r.getCoordinates());

		r.rotate(Direction.RIGHT);
		r.moveForward(3);
		assertEquals(new Coordinates(0, 1, NORTH), r.getCoordinates());

	}

	@Test
	public void test_TraverseArenaClockwise() {
		Robot r = new Robot();

		Coordinates c = new Coordinates(0, 0, SOUTH);
        r.setCoordinates(c);
		
        r.moveForward(5);
        r.rotate(LEFT);
        r.moveForward(5);
        r.rotate(LEFT);
        r.moveForward(5);
        r.rotate(LEFT);
        r.moveForward(5);
        
		assertEquals( new Coordinates(0,0,WEST), r.getCoordinates() );
	}

}
