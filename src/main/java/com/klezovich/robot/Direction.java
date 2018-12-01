package com.klezovich.robot;

import java.util.Random;

public enum Direction {
   FORWARD,BACK,LEFT,RIGHT;
	
	
	public static Direction getRandomLeftOrRight() {
		
		Random r = new Random();
		int randVal = r.nextInt(2);
		
		if( randVal == 0 )
			return LEFT;
		
		return RIGHT;
	}
}
