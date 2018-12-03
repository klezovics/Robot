package com.klezovich.robot.domain.command;

import static com.klezovich.robot.domain.Orientation.SOUTH;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.command.Command;
import com.klezovich.robot.domain.command.ForwardCommand;

public class CommandExecution_Test {

	
	@Test
	public void basicTest() {
		
		Robot r = new Robot( new Coordinates(0,0,SOUTH),5,5 );
		List<Command> cmds = new ArrayList<>();
		cmds.add( new ForwardCommand( new String[] {"1"} ) );
		
		for( Command cmd: cmds ) {
			cmd.execute(r);
		}
		
		assertEquals( new Coordinates(0,1,SOUTH), r.getCoordinates());
	}
}
