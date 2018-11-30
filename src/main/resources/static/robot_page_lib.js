class RobotPageController{
	
	static  getCommandTextAreaId() {
		var commandTextAreaId = "text_area_1";
		return commandTextAreaId;
	}

	static clearCommandTextArea() {
		$("#"+getCommandTextAreaId()).val('');
	}

	static clearRobotData() {
		clearCommandTextArea();
		Robot.remove();
	}
}


class BackEndCommunicationManager{
	
	static formUserScriptRequestObject(url,scriptText) {
		
		var headers = {
				"Content-Type" : 'application/json' // for object property name, use
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
	    
		Robot.draw( response.x, response.y );
		

	}
	
	static processUserScript() {

		var url = window.location.href;
		var scriptProcessingEndpoint = "robots/";

		url = url + scriptProcessingEndpoint;
		var text = $("#" + RobotPageController.getCommandTextAreaId()).val();
		//console.log("Text is" + text);
		//console.log(url);

		var requestObject = BackEndCommunicationManager.formUserScriptRequestObject(url,text);
		//console.log(requestObject);
		//console.log(JSON.stringify(requestObject))

		$.ajax(requestObject).done( function(data) {BackEndCommunicationManager.processScriptResponse(data);} );
		
	}
	
}
