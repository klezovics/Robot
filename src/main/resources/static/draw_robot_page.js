var maxH = 5;
var maxW = 5;
Grid.draw(maxH, maxW);

Robot.draw(0,0);

$("#reset").click( clearRobotData );
$("#submit").click( BackEndCommunicationManager.processUserScript );