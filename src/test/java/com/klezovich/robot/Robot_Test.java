package com.klezovich.robot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static com.klezovich.robot.Direction.*;
import static com.klezovich.robot.Orientation.*;

public class Robot_Test {

	@Test
	public void test_Constructors() {
		
		Coordinates c = new Coordinates(0,0,NORTH);
		Robot r = new Robot(c);
		assertEquals(c, r.getCoordinates() );
		
	}
}
