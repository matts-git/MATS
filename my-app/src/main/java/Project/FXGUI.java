/**
 * Matthew Schuckmann
 * matts.git@gmail.com
 * FXGUI.java 
 * 
This class 'FXGUI' contains fields and methods used to generate a JavaFX GUI user-interface providing event-driven
functionality. The menu is designed to appear at the bottom-center of the screen and run continuously until the stage
window is closed, allowing for repeated generation and presentation of custom quizzes.

Nested class event objects are included which handle passing user input to GenericQuiz.quizMenu().
 */

package Project;

import Project.GenericQuiz.Countdown;
import Project.Problem.AdditionProblem;
import Project.Problem.DivisionProblem;
import Project.Problem.MultiplicationProblem;
import Project.Problem.SubtractionProblem;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Platform;

public class FXGUI extends Application {

	// Class fields
	static String userName;
	static int difficulty = 1;
	static int length = 1;
	Thread t1;
	
	// Setters and getters
	public String getUserName() {
		return userName;
	}
	public static void setUserName(String aName) {
		userName = aName;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public static void setDifficulty(int aDifficulty) {
		difficulty = aDifficulty;
	}
	public int getLength() {
		return length;
	}
	public static void setLength(int aLength) {
		length = aLength;
	}
	
	// Declare and assign text field variables 
	TextField textField1 = new TextField();
	TextField textField2 = new TextField();
	TextField textField3 = new TextField();
	
	// Intent: GUI user menu taking user name, difficulty, and quiz length input from users through text fields and buttons
	// Postcondition: A menu presents text fields for entering name, difficulty and quiz length. Buttons are presented for
	// quiz subject options.
	@Override
	public void start(Stage menuStage) throws Exception {

		// Set text field comments and labels
		textField1.setPromptText("Enter your name.");
		textField2.setPromptText("Enter an integer.");
		textField3.setPromptText("Enter an integer.");
		Label label1 = new Label("Name:");
	    Label label2 = new Label("Difficulty:");
	    Label label3 = new Label("Number of Questions:");
	   
	    // Instantiate object for stage window laying out its children in a single horizontal row
		HBox hbx = new HBox(50);
		hbx.setAlignment(Pos.CENTER);

		// Set menu screen position at instantiation
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		menuStage.setX((primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth()) / 5);
		menuStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() / 15);

		// Menu is always on top of other Windows 'windows'
		menuStage.setAlwaysOnTop(true);

		// Instantiate button objects and associated event objects
		Button addButt = new Button("Addition");
		Button subButt = new Button("Subtraction");
		Button multButt = new Button("Multiplication");
		Button divButt = new Button("Division");
		AddHandler ahandler = new AddHandler();
		SubHandler shandler = new SubHandler();
		MultHandler mhandler = new MultHandler();
		DivHandler dhandler = new DivHandler();
		
		// Set event-driven calls to objects
		addButt.setOnAction(ahandler);
		subButt.setOnAction(shandler);
		multButt.setOnAction(mhandler);
		divButt.setOnAction(dhandler);
		
		// Add text-field and buttons to menu
		hbx.getChildren().addAll(label1, textField1, label2, textField2, label3, textField3, addButt, subButt, multButt, divButt);
		hbx.setSpacing(10);
		
		// Set the menu window title text
		Scene scene = new Scene(hbx);
		menuStage.setTitle("Quiz Selection Menu");
		menuStage.setScene(scene);
		
		// Call to method to display GUI user menu
		menuStage.show();
	}

	/**
	 * 
	 * Beginning of nested event classes
	 *
	 */
	
	// Class handling event corresponding to user menu button selection
	class AddHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {	
					
			// Exception handling to catch forms of unacceptable GUI user menu input 
			try {
				// Assign user input to superclass variables userName, difficulty, and length
				FXGUI.setUserName(textField1.getText());
				FXGUI.setDifficulty(Integer.parseInt(textField2.getText()));
				FXGUI.setLength(Integer.parseInt(textField3.getText()));
				
				// Instantiate Problem and GenericQuiz objects
				Problem thisProb = new AdditionProblem(difficulty);
				GenericQuiz<AdditionProblem> addQuiz = new GenericQuiz<AdditionProblem>(length, difficulty, 4, userName);
				
				// Instantiate runnable Countdown object 
				Countdown cd = new GenericQuiz.Countdown();
				
				// Assign thread to Countdown object
				t1 = new Thread(cd);
				
				// Start Countdown thread and call join() to ensure it has run before calling quizMaker()
				t1.start();
				t1.join();	
				addQuiz.quizMaker(thisProb);
				
				// Close the program
				Platform.exit();
			}
			// Exit on unacceptable user input
			catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Input must be an integer. Program ending.\n");
				System.exit(0);
			}			
		}
	}
	
	// Class handling event corresponding to user menu button selection
	class SubHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			// Exception handling to catch forms of unacceptable GUI user menu input 
			try {
				// Assign user input to superclass variables userName, difficulty, and length
				FXGUI.setUserName(textField1.getText());
				FXGUI.setDifficulty(Integer.parseInt(textField2.getText()));
				FXGUI.setLength(Integer.parseInt(textField3.getText()));
			
				// Instantiate Problem and GenericQuiz objects				
				Problem thisProb = new SubtractionProblem(difficulty);
				GenericQuiz<SubtractionProblem> subQuiz = new GenericQuiz<SubtractionProblem>(length, difficulty, 4, userName);
				
				// Instantiate runnable Countdown object 
				Countdown cd = new GenericQuiz.Countdown();
				
				// Assign thread to Countdown object
				t1 = new Thread(cd);
				
				// Start Countdown thread and call join() to ensure it has run before calling quizMaker()
				t1.start();
				t1.join();	
				subQuiz.quizMaker(thisProb);
		
				// Close the program
				Platform.exit();
			}
			// Exit on unacceptable user input
			catch (Exception ex) {
				System.out.println("Input must be an integer. Program ending.\n");
				System.exit(0);
			}
		}
	}
	
	// Class handling event corresponding to user menu button selection
	class MultHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			try {
				// Exception handling to catch forms of unacceptable GUI user menu input 
				FXGUI.setUserName(textField1.getText());
				FXGUI.setDifficulty(Integer.parseInt(textField2.getText()));
				FXGUI.setLength(Integer.parseInt(textField3.getText()));
			
				// Instantiate Problem and GenericQuiz objects				
				Problem thisProb = new MultiplicationProblem(difficulty);				
				GenericQuiz<MultiplicationProblem> multQuiz = new GenericQuiz<MultiplicationProblem>(length, difficulty, 4, userName);
				
				// Instantiate runnable Countdown object 
				Countdown cd = new GenericQuiz.Countdown();
				
				// Assign thread to Countdown object
				t1 = new Thread(cd);
				
				// Start Countdown thread and call join() to ensure it has run before calling quizMaker()
				t1.start();
				t1.join();	
				multQuiz.quizMaker(thisProb);
				
				// Close the program
				Platform.exit();
			}
			// Exit on unacceptable user input
			catch (Exception ex) {
				System.out.println("Input must be an integer. Program ending.\n");
				System.exit(0);
			}
		}
	}
	
	// Class handling event corresponding to user menu button selection
	class DivHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			try {
				// Exception handling to catch forms of unacceptable GUI user menu input 
				FXGUI.setUserName(textField1.getText());
				FXGUI.setDifficulty(Integer.parseInt(textField2.getText()));
				FXGUI.setLength(Integer.parseInt(textField3.getText()));
			
				// Instantiate Problem and GenericQuiz objects				
				Problem thisProb = new DivisionProblem(difficulty);				
				GenericQuiz<DivisionProblem> divQuiz = new GenericQuiz<DivisionProblem>(length, difficulty, 4, userName);
				
				// Instantiate runnable Countdown object 
				Countdown cd = new GenericQuiz.Countdown();
				
				// Assign thread to Countdown object
				t1 = new Thread(cd);
				
				// Start Countdown thread and call join() to ensure it has run before calling quizMaker()
				t1.start();
				t1.join();	
				divQuiz.quizMaker(thisProb);
				
				// Close the program
				Platform.exit();
			}
			// Exit on unacceptable user input
			catch (Exception ex) {
				System.out.println("Input must be an integer. Program ending.\n");
				System.exit(0);
			}
		}
	}

	// Main method used to launch the JavaFX GUI user menu
	public static void main(String[] args) throws InterruptedException {

		launch(args);
 
    }
	

}



