package nonJunitTests;
/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  MainNoJunitTest.java
 *
Non-JUnit tests for custom exception CustomExceptions.MalformedHighScoreException.java. This exception deals with file I/O, thus
necessitating a non-JUnit testing strategy. Here, records.dat t is deleted before running HighScore.recordWriter() to ensure the 
method throws a MalformedHighScoreException, which is caught by GenericQuiz.quizMaker().
*/


import java.io.File;

import CustomExceptions.MalformedHighScoreException;
import Project.GenericQuiz;
//import Project.AdditionProblem;
import Project.Problem;

public class MalformedHighScoreExceptionTest {
	
	public static void main(String[] args) {
		malformedHighScoreExceptionTest();
	}

	// Precondition: either local file records.dat exists or it does not
	// Postcondition: MalformedHighScoreException is handled and a new records.dat with default content is created
	public static  void malformedHighScoreExceptionTest() {

		// Delete any existing occurrence of local records.dat file 
		File scoreFile = new File("records.dat");
		if(scoreFile.delete()) 
        { 
            System.out.println("File deleted successfully\n"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 

		// Instantiate Problem object for testing purposes
		Problem p = new Problem.AdditionProblem();
		
		// Handle the expected throwing of MalformedHighScoreException
		try {
			GenericQuiz.HighScore.recordWriter("matt", p, 120);	
		}
		catch (MalformedHighScoreException e) {
			System.out.println("\nTest successful - new records.dat created.");
		}
	}
}
