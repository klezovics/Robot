package com.klezovich.robot.service;

import com.klezovich.robot.domain.Coordinates;
import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;

public interface ScriptExecutionService {

	public Coordinates executeScript( Script script ) throws ScriptExecutionException;
}
