package com.klezovich.robot;

import static com.klezovich.robot.Direction.LEFT;
import static com.klezovich.robot.Direction.RIGHT;
import static com.klezovich.robot.Orientation.EAST;
import static com.klezovich.robot.Orientation.NORTH;
import static com.klezovich.robot.Orientation.SOUTH;
import static com.klezovich.robot.Orientation.WEST;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Robot_Test {

	@Test
	public void test_Constructors() {

		Coordinates c = new Coordinates(0, 0, NORTH);
		Robot r = new Robot(c);
		assertEquals(c, r.getCoordinates());

		r = new Robot();
		r.setCoordinates(c);
		assertEquals(c, r.getCoordinates());

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
