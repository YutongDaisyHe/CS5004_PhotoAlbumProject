# Shapes Photo Album View and Controller
This is the complete shapes photo album application using MVC.

Author: Yutong He

## Getting Started

### Executing program

* Copy the 5004HW9.jar file from resources directory with command input txt files to a common folder.
* Open a command-prompt/terminal and navigate to that folder. 
* Type:


    java -jar 5004HW9.jar -in demo_input.txt -view web -out out.html

*  Press ENTER.

## Description
### resources directory
* 5004HW9.jar
* buildingsOut.html
* README.md

### src directory shapesphotoalbum package
### model package
(src/shapesphotoalbum/model)
* IPhotoAlbumModel
* PhotoAlbumModel
* IShape
* AbstractShape
* Oval
* Rectangle
* Snapshot

#### Design Changes in Model
1. Shapes' variable type was changed from double into integer. The given commands from the command 
files use integer types and the Swing graphics take integer for coloring drawing. Therefore, I made
this revision on model and shape classes.


2. Deleted the original enum Color class from the model. Instead, the model takes r, g, b color 
variables as input separately. I realized that the java.awt.Color class functions in similar ways as
my self-designed enum Color class while implementing the graphical view. Separating r, g, b 
variables makes it easier to parse or transmit the shape information, and plug the color information
to the java.awt.Color class for displaying colors.


3. Changed getHistoryList return type from string to List<String> in the model to access the 
snapshot id more conveniently when needed. Added a printSnapshotIds method for output the list of id
string which keeps the original function of the getHistoryList method.


4. Deleted the Point helper class from the model. The model takes in dimensions of shapes separately
rather than using a Point object to wrap up the x and y variables.


5. Added the case sensitivity requirement to the shape id/name.


6. Deleted the requirement on shape location. A shape can be anywhere even it is located outside the
range of the canvas.


Modifications of design for the future:
According to the TA's feedback on HW8, my current model design on taking snapshot parts would bring 
in lots of work on parsing String in HW9. It did take me a while parsing snapshot information and 
shape information for HW9. If I were to modify this project in the future, I may consider taking a 
different approach for keeping a record of snapshots rather than keeping them as Strings or list of
Strings.

### controller package 
(src/shapesphotoalbum/controller)
* IPhotoAlbumController: Interface of AbstractController and concrete controller classes 
PhotoAlbumGraphicalController and PhotoAlbumWebController.


* AbstractController


* PhotoAlbumGraphicalController: The controller for communicating between the graphical view and the
model. It has a go() method and an actionPerformed() method.


* PhotoAlbumWebController: The controller for communicating between the web view and the model. It
has a go() method.

### view package
(src/shapesphotoalbum/view)
* IPhotoAlbumView: Interface of view classes PhotoAlbumGraphicalView and PhotoAlbumWebView.


* AbstractController


* PhotoAlbumGraphicalView: The graphical view GUI of displaying snapshot information passed in 
through graphical controller. It has a paint() method for drawing the default/first snapshot photo 
information to the GUI window. A repaint() method for drawing the new snapshot photos. A 
setSnapshotString() method for resetting the snapshot information for drawing. A setActionListener()
method for listening to the button action commands. A popSelection() method for displaying the menu 
of snapshot ids in a pop-up window and responding to user's selections. The other methods are helper
methods used for JUnit testing.


* HeaderPanel: the concrete class object used in graphical view to draw the snapshot id and 
description on the top of the GUI window.


* PhotoPanel: the concrete class object used in graphical view to draw the snapshot photos at the 
center of the GUI window.


* ButtonPanel: the concrete class object used in graphical view to display the menu buttons.


* PhotoAlbumWebView: The web/html+svg view of displaying snapshot information. It has a paint() 
method for writing the html+svg Strings into the target html file. This method uses helper 
method and class object to achieve its goal: the HtmlAlbumAdapter object (in the helper package) is 
initialized to transmit the regular text representation of the album snapshots into html and svg 
formatted text. The writeHtml private method is used to write the html formatted text to the target
html file. The setSnapshotString() method is used to initialize the snapshot string for 
transmitting. The other methods are helper methods used in JUnit testing.

### helper package
(src/shapesphotoalbum/helper)
* AlbumBuilder: This is a utility/helper class to set up the photo album according to the read-in 
commands from the txt file. It is used in both PhotoAlbumGraphicalController and 
PhotoAlbumWebController. This class uses factory design and has only one static method setUpAlbum().


* CommandInputParser: This is a helper class to use regular expressions to parse the input command 
line to obtain -in source file, -view/-v view type, optional -out html file, xmax, and ymax.


* HtmlAlbumAdapter: This helper class is initialized in the PhotoAlbumWebView class to transmit the 
regular text representation of the album snapshots into html and svg formatted text.


* SnapshotParser: This is a helper class to parse details of a snapshot to obtain information for 
drawing graphics presented in both web and graphical Views. It is used in HeaderPanel class, 
HtmlAlbumAdapter class, and PhotoPanel class inside the view package.

### PhotoAlbumMain
(src/shapesphotoalbum)
* Entry-point for the Shape Photo Album application. It hands most of the control over to MVC.

### test directory 
* commandfiles package: It contains command files for JUnit testing.
* htmlfiles package: This package is used to store output html files from JUnit testings.
* controllertest package: It contains JUnit tests for controllers.
* viewtest package: It contains JUnit tests for views.
* modeltest package: It contains JUnit tests for the model.
* helpertest package: It contains JUnit tests for helper classes.
