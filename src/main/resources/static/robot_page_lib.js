var commandTextAreaId = "#text_area_1";
var robotElementId = "#robot";

function clearCommandTextArea() {
	$(commandTextAreaId).val('');
}

function removeRobot() {
	$(robotElementId).remove();
}

function clearRobotData(){
	clearCommandTextArea();
	removeRobot();
}

function processUserScript(){
	
	var url = window.location.href;
	url = url + "robots/";
	var text = $('#text_area_1').val();
	console.log("Text is" + text);
	console.log(url);

	var headers = {
		"Content-Type" : 'application/json' //for object property name, use quoted notation shown in second
	}

	var requestObject = {
		url : url,
		headers : headers,
		type : 'post',
		data : text
	}

	console.log(requestObject);
	console.log(JSON.stringify(requestObject))
	$.ajax(requestObject).done(function(data) {
		if (console && console.log) {
			console.log("Sample of data:", data);
		}
	});
}

