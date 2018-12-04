

class Robot{
	
	constructor(grid){
		this.grid=grid;
	}
	
    getRobotId(){
    	return "robot";
    }
	
	createRobot(){
		
		let robot = document.createElement("img");
		robot.id=this.getRobotId();
		robot.width="50";
		robot.height="50";
		robot.src="/images/robot_image.png";
		return robot;
	}
	
	draw( x,y ){
		this.remove();
		let queryStr ="#"+this.grid.calcGridCellId(x,y);
		var robot = this.createRobot();
		$(queryStr).append(robot);
	}
	
	draw( x,y,orientation ){
		this.remove();
		let queryStr ="#"+this.grid.calcGridCellId(x,y);
		var robot = this.createRobot();
		$(queryStr).append(robot);
		this.rotateRobot(orientation);
	}
	
	rotateRobot( orientation ){
		
		let robotId = this.getRobotId();
		
		
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
	
	remove(){
		let robotId = this.getRobotId();
	    $( "#"+robotId ).remove();	
	}
	
}

