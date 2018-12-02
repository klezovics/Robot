

class Robot{
	
    static getRobotId(){
    	return "robot";
    }
	
	static createRobot(){
		
		let robot = document.createElement("img");
		robot.id=Robot.getRobotId();
		robot.width="50";
		robot.height="50";
		robot.src="/images/robot_image.png";
		return robot;
	}
	
	static draw( x,y ){
		Robot.remove();
		let queryStr ="#"+Grid.calcGridCellId(x,y);
		var robot = Robot.createRobot();
		$(queryStr).append(robot);
	}
	
	static draw( x,y,orientation ){
		Robot.remove();
		let queryStr ="#"+Grid.calcGridCellId(x,y);
		var robot = Robot.createRobot();
		$(queryStr).append(robot);
		Robot.rotateRobot(orientation);
	}
	
	static rotateRobot( orientation ){
		
		let robotId = Robot.getRobotId();
		
		
		if( orientation === "NORTH" ){
			$("#"+robotId).rotate(90);
		}else if( orientation === "SOUTH" ){
			$("#"+robotId).rotate(270);
		}else if( orientation === "WEST" ){
			$("#"+robotId).rotate(0)
		}else if( orientation === "EAST" ){
			$("#"+robotId).rotate(180)
		}
	}
	
	static remove(){
		let robotId = Robot.getRobotId();
	    $( "#"+robotId ).remove();	
	}
	
}

