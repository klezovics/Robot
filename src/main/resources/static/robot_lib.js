

class Robot{
	
    static getRobotId(){
    	return "robot";
    }
	
	static createRobot(){
		
		let robot = document.createElement("img");
		robot.id=Robot.getRobotId();
		robot.width="50";
		robot.height="50";
		robot.src="Robot.png";
		return robot;
	}
	
	static draw( squareId ){
		Robot.remove();
		let queryStr ="#"+squareId;
		var robot = Robot.createRobot();
		$(queryStr).append(robot);
	}
	
	static remove(){
		let robotId = Robot.getRobotId();
	    $( "#"+robotId ).remove();	
	}
	
}

function drawRobot(squareId) {
	document.getElementById(squareId).innerHTML = '<img src="Robot.png" id="robot" width="50px" height="50px" />';
}
