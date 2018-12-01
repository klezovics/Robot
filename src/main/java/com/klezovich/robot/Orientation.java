package com.klezovich.robot;

import static com.klezovich.robot.Direction.LEFT;
import static com.klezovich.robot.Direction.RIGHT;
import static com.klezovich.robot.Orientation.EAST;
import static com.klezovich.robot.Orientation.NORTH;
import static com.klezovich.robot.Orientation.SOUTH;
import static com.klezovich.robot.Orientation.WEST;

public enum Orientation {
	WEST, EAST, NORTH, SOUTH;

	Orientation rotate(Direction rotationDir) {

		if (rotationDir.equals(LEFT)) {

			switch (this) {
			case NORTH:
				return WEST;

			case SOUTH:
				return EAST;

			case EAST:
				return NORTH;

			case WEST:
				return SOUTH;

			}
		}

		if (rotationDir.equals(RIGHT)) {

			switch (this) {
			case NORTH:
				return EAST;

			case SOUTH:
				return WEST;

			case EAST:
				return SOUTH;

			case WEST:
				return NORTH;

			}
		}

		return null;
	}
}
