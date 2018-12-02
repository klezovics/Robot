class RobotPageController{
	
	static  getCommandTextAreaId() {
		var commandTextAreaId = "text_area_1";
		return commandTextAreaId;
	}

	static clearCommandTextArea() {
		$("#"+RobotPageController.getCommandTextAreaId()).val('');
	}


	
	static clearRobotData() {
		RobotPageController.clearCommandTextArea();
		Robot.remove();
	}
	
	static insertSampleScript(){
		
		RobotPageController.clearCommandTextArea();
		
		let qs = "#"+RobotPageController.getCommandTextAreaId();
		$(qs).val( $(qs).val() + 'POSITION 1 3 EAST //sets the initial position for the robot\n' );
		$(qs).val( $(qs).val() + 'FORWARD 3 //lets the robot do 3 steps forward\n' );
		$(qs).val( $(qs).val() + 'WAIT //lets the robot do nothing\n' );
		$(qs).val( $(qs).val() + 'TURNAROUND //lets the robot turn around\n' );
		$(qs).val( $(qs).val() + 'FORWARD 1 //lets the robot do 1 step forward\n' );
		$(qs).val( $(qs).val() + 'RIGHT //lets the robot turn right\n' );
		$(qs).val( $(qs).val() + 'FORWARD 2 //lets the robot do 2 steps forward\n' );
	
	} 
}


class BackEndCommunicationManager{
	
	static formUserScriptRequestObject(url,scriptText) {
		
		var headers = {
				"Content-Type" : 'application/json' // for object property name,
													// use
			// quoted notation shown in second
			}

			var requestObject = {
				url : url,
				headers : headers,
				type : 'post',
				data : scriptText
			}
		
		return requestObject;
	}
	
	static processScriptResponse(data) {

		console.log(data);
		
		var response = jQuery.parseJSON(JSON.stringify(data));
	    
		Robot.draw( response.x, response.y, response.orientation );
		
	}
	
	static processUserScript() {

		var url = window.location.href;
		var scriptProcessingEndpoint = "robots/";

		url = url + scriptProcessingEndpoint;
		var text = $("#" + RobotPageController.getCommandTextAreaId()).val();
		// console.log("Text is" + text);
		// console.log(url);

		var requestObject = BackEndCommunicationManager.formUserScriptRequestObject(url,text);
		// console.log(requestObject);
		// console.log(JSON.stringify(requestObject))

		$.ajax(requestObject).done( function(data) {BackEndCommunicationManager.processScriptResponse(data);} );
		
	}
	
}
