package com.klezovich.robot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klezovich.robot.domain.Robot;
import com.klezovich.robot.domain.Script;
import com.klezovich.robot.domain.command.Command;
import com.klezovich.robot.domain.command.CommandParser;
import com.klezovich.robot.domain.command.exception.ScriptExecutionException;
import com.klezovich.robot.json.JsonErrorDto;

@Controller
public class FrontEndController {

	@GetMapping("/")
	public String start() {
		return "login";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/robot_control_page")
	public String getRobotControlPage(Model m, Principal p) {

		if (p != null)
			m.addAttribute("userName", p.getName());
		else
			m.addAttribute("userName", "Anonymous");

		return "robot_control_page";
	}

}
