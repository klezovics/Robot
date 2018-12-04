package com.klezovich.robot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;
import com.klezovich.robot.json.JsonErrorDto;
import com.klezovich.robot.service.ScriptExecutionService;

@Controller
public class ScriptExecutionRequestController {

	@Autowired
	ScriptExecutionService scriptExecutionService;
	
	@PostMapping(value = "/robot_control_page/robots/")
	@ResponseBody
	public Object getRobotMovements(@RequestBody @Valid Script script, ModelMap m) {
		
		try {
			return scriptExecutionService.executeScript(script);
		}catch( ScriptExecutionException e ) {
			return new JsonErrorDto(e);
		}
		
	}
}