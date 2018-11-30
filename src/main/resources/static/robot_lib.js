

class Robot{
	
	constructor(){
		var robot = document.createElement(img);
		robot.id="robot";
		robot.width="50px";
		robot.height="50px";
		src="Robot.png";
		this._robot = robot
	};
	
	draw( squareId ){	   	
		$("#"+squareId).appendChild(robot);
	}
	
	static remove(){
	   this._robot.remove();	
	}
	
	
}

function drawRobot(squareId) {
	document.getElementById(squareId).innerHTML = '<img src="Robot.png" id="robot" width="50px" height="50px" />';
}
