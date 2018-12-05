package com.klezovich.robot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;
import com.klezovich.robot.json.JsonErrorDto;
import com.klezovich.robot.service.ScriptExecutionService;

@RestController
public class ScriptExecutionRequestController {

	@Autowired
	ScriptExecutionService scriptExecutionService;
	
	@PostMapping(value = "/robot_control_page/robots/")
	public Object getRobotMovements(@RequestBody @Valid Script script) {
		
		try {
			return scriptExecutionService.executeScript(script);
		}catch( ScriptExecutionException e ) {
			return new JsonErrorDto(e.toString());
		}
		
	}
}
