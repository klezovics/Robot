

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
	
	static draw( x,y ){
		Robot.remove();
		let queryStr ="#"+Grid.calcGridCellId(x,y);
		var robot = Robot.createRobot();
		$(queryStr).append(robot);
	}
	
	static remove(){
		let robotId = Robot.getRobotId();
	    $( "#"+robotId ).remove();	
	}
	
}

