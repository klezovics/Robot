class RobotPageController{
	
    constructor(robot,grid){
		this.robot=robot;
		this.grid= grid;
	}
	
    getClearBttnListener(){
    	var boundFunction = ( function() {this.clearRobotData() }  ).bind(this);
    	return boundFunction;
    }
    
    getSampleScriptBttnListener(){
    	var boundFunction = ( function() { this.insertSampleScript() }  ).bind(this);
    	return boundFunction;
    }
    
	getCommandTextAreaId() {
		var commandTextAreaId = "text_area_1";
		return commandTextAreaId;
	}

	clearCommandTextArea() {
		$("#"+this.getCommandTextAreaId()).val('');
		return 1;
	}
    
	getSuccessPromptId(){
		return "success";
	}
	
	getErrorPromptId(){
		return "error";
	}
	
	clearSuccessPrompt(){
		$("#"+this.getSuccessPromptId() ).remove(); 
	}
	
	clearErrorPrompt(){
		$("#"+this.getErrorPromptId()).remove();
	}
	
	drawErrorPrompt(errorStr){
		
		this.clearSuccessPrompt();
		this.clearErrorPrompt();
		
		let error = document.createElement("div");
		error.id=RobotPageController.getErrorPromptId();
		$("#prompt_container").prepend(error);
		$("#error").addClass("alert alert-danger");
		$("#error").html("Error: '"+errorStr+"'");
		$("#error").html( $("#error").html() + '<button type="button" class="close"\
				             data-dismiss="alert" aria-label="Close"> \
                             <span aria-hidden="true">&times;</span> \
                             </button>');
		
	}
	

	
	drawSuccessPrompt(){
		
		this.clearSuccessPrompt();
		this.clearErrorPrompt();
		
		let success = document.createElement("div");
		success.id=this.getSuccessPromptId();
		$("#prompt_container").prepend(success);
		$("#success").addClass("alert alert-success");
		$("#success").html('Script executed - OK! <button type="button" class="close"\
				             data-dismiss="alert" aria-label="Close"> \
                             <span aria-hidden="true">&times;</span> \
                             </button>');
		
	}
	

	clearRobotData() {
		this.clearCommandTextArea();
		this.clearSuccessPrompt();
		this.robot.remove();
	}
	
	insertSampleScript(){
		
		this.clearCommandTextArea();
		
		let qs = "#"+this.getCommandTextAreaId();
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
	
	constructor( robotPageController ){
		this.robotPageController = robotPageController;
	}
	
	 getSubmitBttnListener(){
	    	var boundFunction = ( function() {this.processUserScript() }  ).bind(this);
	    	return boundFunction;
	    }
	    
	
	formUserScriptRequestObject(url,scriptText) {
		
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
	
	processScriptResponse(data) {

		console.log(data);
		
		var response = jQuery.parseJSON(JSON.stringify(data));
	    console.log(response);
		
		if( response.hasOwnProperty('error') ){
			console.log("Error caught")
			this.robotPageController.drawErrorPrompt(response.error);
			return;
		}
		
		this.robotPageController.robot.draw( response.x, response.y, response.orientation );
		this.robotPageController.drawSuccessPrompt();
		
	}
	
	processUserScript() {

		console.log("Sending script to back-end");
		
		var url = window.location.href;
		var scriptProcessingEndpoint = "/robots/";

		url = url + scriptProcessingEndpoint;
		var text = $("#" + this.robotPageController.getCommandTextAreaId()).val();
		// console.log("Text is" + text);
		// console.log(url);

		var requestObject = this.formUserScriptRequestObject(url,text);
		// console.log(requestObject);
		// console.log(JSON.stringify(requestObject))

		//var boundFunction = ( function() {this.processScriptResponse }  ).bind(this);
		var self = this;
		$.post(requestObject).done( function(data) {self.processScriptResponse(data);} );
		
	}
	
}
