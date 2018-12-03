var maxX = 5;
var maxY = 5;


let grid = new Grid(maxX, maxY);
grid.draw();

let robot = new Robot(grid);

let robotPageController = new RobotPageController(robot,grid);
let backEndCommunicationManager = new BackEndCommunicationManager(robotPageController);

let clearBttnListener = robotPageController.getClearBttnListener();
let sampleScriptBttnListener = robotPageController.getSampleScriptBttnListener();
let submitBttnListener = backEndCommunicationManager.getSubmitBttnListener();
let setSizeBttnListener = robotPageController.getSetSizeBttnListener();

$("#clear").click( clearBttnListener );
$("#submit").click( submitBttnListener );
$("#sample_script_button").click( sampleScriptBttnListener );
$("#set_size_button").click( setSizeBttnListener );

//var json = '{"x":0,"y":0,"orientation":"WEST"}'
//var obj = JSON.parse(json);
//console.log(obj);

