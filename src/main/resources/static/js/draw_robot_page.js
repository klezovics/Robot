var maxH = 5;
var maxW = 5;
Grid.draw(maxH, maxW);

Robot.draw(0,0);

$("#reset").click( RobotPageController.clearRobotData );
$("#submit").click( BackEndCommunicationManager.processUserScript );
$("#sample_script_button").click( RobotPageController.insertSampleScript );

var json = '{"x":0,"y":0,"orientation":"WEST"}'
var obj = JSON.parse(json);
console.log(obj);

