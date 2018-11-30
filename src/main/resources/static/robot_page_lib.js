
function getCommandTextAreaId() {
	var commandTextAreaId = "text_area_1";
	return commandTextAreaId;
}

function clearCommandTextArea() {
	$("#"+getCommandTextAreaId()).val('');
}

function clearRobotData() {
	clearCommandTextArea();
	Robot.remove();
}

class BackEndCommunicationManager{
	
	static formUserScriptRequestObject(scriptText) {
		
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
	}
	
	static processScriptResponse(data) {

		if (console && console.log) {
			console.log("Sample of data:", data);
		}

	}
	
	static processUserScript() {

		var url = window.location.href;
		var scriptProcessingEndpoint = "robots/";

		url = url + scriptProcessingEndpoint;
		var text = $("#" + getCommandTextAreaId()).val();
		console.log("Text is" + text);
		console.log(url);

		var requestObject = BackEndCommunicationManager.formUserScriptRequestObject();
		console.log(requestObject);
		console.log(JSON.stringify(requestObject))

		$.ajax(requestObject).done( BackEndCommunicationManager.processScriptResponse(data) );
	}
	
	
}
