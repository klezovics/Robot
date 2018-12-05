# RobotScript interpreter application #

## Summary ## 

In order to run the application you can use one of the following:
1) Import it into your IDE and select 'Run as SpringBoot application ...' 
2) Run it using maven 

The application runs on port *5000*. Therefore, if you are running a local instance 
use the following URL: *'http://localhost:5000/'*  

In order to use this application - you will need to *sign in* as one of the registered users.
These are the following: 
1) Name: 'user'   Password: 'secret256' Roles: USER
2) Name: 'admin'  Password: 'secret256'  Roles: ADMIN 

The difference between a normal user and an admin is that the *admin can resize the grid*.
Therefore, for testing puproses it is best to log in as the admin user.

This application allows the user to control a robot on a grid. 
The user types in a script on the page and clicks "Submit". 

The script is then sent to the back end using AJAX, where it is processed. 
The resulting position of the robot is sent back 
to the user's web page and the final position of the robot 
after script execution is shown to the user. 

## Application Architecture ##

Here's the list of used technologies

Front-end: 
1) Javascript 
2) jQuery - selection of elements and DOM manipulation
3) Bootstrap - ready made CSS styles
4) jQueryRotate - used for rotating the robot
5) Thymeleaf - template engine for Spring Boot 

Back-end: 
1) Java 
2) Spring Boot 
3) Maven - chosen build tool 
4) Spring Security - securing the application 
5) JUnit - testing 

### Front-end ### 

The application has two pages - a login page and the main application page. 

Both pages are implemented using Thymeleaf as a base technology. It allows 
for conditional display of page content based on user permissions (only Admin can see the grid)
and insertion of fragments from other pages. For example, you can define a navigation bar 
in a separate page and then insert it into all other pages. 

Bootstrap is used for styling the page. jQuery is used for its selectors and DOM manipulation. 

The only page with custom JS code is the main application page. 

The JS code for page control is written in an OOP style and consists of the following classes, 
which are define in separate javascript files. All javascript files ( along with other resources ) 
reside in the */src/main/resources/* folder. 

Here's the list of classes: 
Robot -> robot_lib.js -> used for drawing the robot 
Grid -> grid_lib.js -> used for drawing the grid 
RobotPageContoller -> robot_lib.js - > its main purpose is DOM manipulation - display of 
success and error prompts 
BackendCommunicationManager -> robot_lib.js -> sending the user script to server and 
processing the resposnse.  

Here's how the classes are organized relative to each other: 
Robot and Grid do not have any dependencies. 
RobotPageController HAS-A Robot and HAS-A Grid
BackendCommunicationmanager HAS-A RobotPageController

A sort of a *main method* for the main page is the file 'draw_robot_page.js'. 
It creates the necessary objects using constructors and attaches listeners to the DOM 
elements of the web page.

Each listener function is a member of either RobotPageController or BackendCommunicationManager. 

The architecture could be further improved by implementing the module JS pattern 
similar to here ( http://www.adequatelygood.com/JavaScript-Module-Pattern-In-Depth.html ) and
by reducing "coupling" with the current HTML pages.


### Front-end functionality ### 

Once the user logs in - he is met with a greeting 'Hello, <username>'.
The user can press a button to logout or he can use the application. 

There are two main areas on the page - the script input area and the grid area. 

In the script input area the user can do the following: 
1) Enter the script into a text area
2) Press 'Submit' button to submit the script to the server 
3) Press 'Clear' button to clear the text area
4) Press 'Sample Script' to see a sample script

In the grid area the user can resize the grid (only if he has admin privileges) 
Grid needs to have a size of at least one and it cannot be more than 5. 

If there's an *error in the users script* - it won't be executed - instead an error 
message will be displayed. This message contains the line number and the description 
of the essence of the problem. This makes user scripts easy to debug.

In this aspect the project could be further improved by switching from a "compilation" 
strategy to a "interpretation" strategy. Currently, even if one command is wrong - 
the script is *NOT* instead an error with a line number is displayed. 

It would be better to display the position of the robot after executing the "correct" 
portion of the script - this would make the application even easier to debug.

Additionally, by using CSS animation or transition functionality the robot's 
movements could be visualized for the user - that is a transition would be rendered 
for every line of the script. 

## Back-end ##

The back-end of the project will be described by describing each of the layers. 

### Web layer ### 

All of the logic related to handling requests is stored in the 
com.klezovich.robot.controller package. 

The application has two controller classes.

One controller 'FrontEndController' is responsible for end-points which are 
accessed by the user using a browser. 

The other 'ScriptExecutionRequestController' is responsible for a single end-point 
which the app uses to get the results of the script execution. An AJAX 'POST' request 
is sent there and based on the contents of the response - either the robot is drawn 
or an error message is displayed. 

If the user *resizes the grid* this information will be sent to the server with the 
next script and will be used in the calculations.

### Service layer #### 

The service layer contains only one service - ScriptExecution service. 
It contains one method .executeScript(). This method will either 
return the final position of the robot - (x,y,orientation ) or throw 
a ScriptExecutionException. 

### Domain layer ### 

The key idea in the domain layer is to use the *command pattern*. 
This is implemented by using two classes: 
1) Robot
2) Command

The first is a class which implements the robot functionality. Its two principal 
methods are .moveForward(Integer distance) and rotate( Direction direction ).
Internally, it stores its coordinate and orientation. 

The Command class is an abstract class.  Each of the classes, which extend it 
represent a particular command. They have two methods which need to be overriden 
.execute( Robot r ) and validate(). 

The .execute( Robot r ) method taken in an instance of Robot objects and calls its
relevant methods. For example an instance of the MoveForwardCommand class from 
inside this method will call the robot's .moveForward() method. 

The script is parsed by a class called CommandParser. It pre-processes the lines (removal of comments,
 removal of empty lines) and then sees what commands are called and creates an instance of the 
 relevant command object and sets its arguments.
 
 ### Persistance layer ### 
 
 Persistance is not necessary for this project and therefore it is not used.
 
 ## Testing ## 
 
 The application is tested using the JUnit framework. 
 All of tests can be found in the "src/test/java" folder and all tests for classes
 in a particular package are located in the same package of the test folder. 
 
 Most of the unit tests are fairly basic, however, there is one very interesting component.
 
 Given that the problem at hand is not particularly challenging here's what we can do .. 
 
 In order to test the ScriptExecutionService ( takes the script fron the front-end and produces the final position )
 we can hard-code scenarios and see if they are executed correctly. 
 
 However, we can do something better ...
 
 A class *com.klezovich.robot.service.implementation.ScriptGenerator* as an input to its 
 .generateScript() methods takes two points (with orientation) - a start point and the end-point. 
 
 It then *automatically generates the text of the script* (which is the same as what the user could type in), which is then fed to the 
 ScriptExecutionService.  And then it checks if the final position is equal to the final 
 position of the input to the .generateScript() method ...  
 
 For example, a 1000 random scenarios for grids of sizes from 1 to 50 are done in the 
 automatedTestSequence() method of the ScriptExecutionService method. 
 
 