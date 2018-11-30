var maxH = 5;
var maxW = 5;
Grid.draw(maxH, maxW);

Robot.draw("grid_cell_0x0");

$("#reset").click( clearRobotData );
$("#submit").click( processUserScript );