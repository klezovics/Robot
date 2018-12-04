package com.klezovich.robot.service.implementation;

import static com.klezovich.robot.domain.Orientation.*;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Direction;
import com.klezovich.robot.domain.Orientation;
import com.klezovich.robot.domain.Script;

public class ScriptGenerator {

	private static void moveForward( int distance, StringBuilder sb ) {
	    sb.append("FORWARD " + distance + "\n");	
	}
	
	private static void turnRobot(Orientation source, Orientation target, StringBuilder sb) {

		if (source != target) {
			while (source != target) {
				sb.append("LEFT\n");
				source = source.rotate(Direction.LEFT);
			}
		}
	}




	private static void moveRobotAlongYAxis(int y1, int y2, Orientation start, Orientation target, StringBuilder sb) {
		
		
		if (y1 < y2) {
			turnRobot( start, SOUTH, sb);
			moveForward( y2-y1, sb);
			turnRobot(SOUTH,target, sb);
		}

		if (y1 == y2) {
			turnRobot( start,target, sb);
		}

		if (y1 > y2) {
			turnRobot( start, NORTH, sb);
			moveForward(y1-y2, sb);
			turnRobot(NORTH, target, sb);
		}
	}

	public static Script generateScript(Coordinates start, Coordinates finish) {

		Script script = new Script();
		StringBuilder sb = new StringBuilder();

		int x1 = start.getX();
		int y1 = start.getY();
		Orientation startOrient = start.getOrientation();

		int x2 = finish.getX();
		int y2 = finish.getY();
		Orientation finishOrient = finish.getOrientation();

		sb.append("POSITION " + x1 + " " + y1 + " " + start.getOrientation().toString() + "\n");

		// Ensure robot starts looking north
        turnRobot(startOrient, NORTH, sb);

		// Easy case - start and finish have the same x coordinate
		if (x1 == x2) {
			moveRobotAlongYAxis(y1, y2, NORTH, finishOrient, sb);
		} else if (x1 < x2) {
			sb.append("RIGHT\n");
			moveForward(x2-x1, sb);
			sb.append("LEFT\n");
			moveRobotAlongYAxis(y1, y2, NORTH ,finishOrient, sb);

		} else if (x1 > x2) {
			sb.append("LEFT\n");
			moveForward( x1-x2, sb );
			sb.append("RIGHT\n");
			moveRobotAlongYAxis(y1, y2, NORTH ,finishOrient, sb);
		}

		script.setText(sb.toString());
		script.setMaxX(5);
		script.setMaxY(5);
		return script;
	}
}
