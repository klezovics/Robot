package com.klezovich.robot.service;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Script;

public interface ScriptExecutionService {

	public Coordinates executeScript( Script script );
}
