/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  MainNoJunitTest.java
 *
Non-JUnit tests for custom exception CustomExceptions.MalformedHighScoreException.java. This exception deals with file I/O, thus
necessitating a non-JUnit testing strategy. Here, scores.txt is deleted before running Problem.scoreFileUpdate() to ensure the 
method throws a MalformedHighScoreException.
*/

package customTests;

import java.io.File;
import app.GenericQuiz;
import app.Problem;
import customExceptions.MalformedHighScoreException;

public class HighScoreTest {
	
	public static void main(String[] args) {
		malformedHighScoreExceptionTest();
	}

	// Precondition: either local file scores.txt exists or it does not
	// Postcondition: MalformedHighScoreException is handled and a new records.dat is created
	public static  void malformedHighScoreExceptionTest() {

		// Delete any existing occurrence of local scores.txt file 
		File scoreFile = new File("record.dat");
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
			GenericQuiz.HighScore.recordWriter("Test name", p, 120);	
		}
		catch (MalformedHighScoreException e) {
			System.out.println("\nTest successful - new scores.txt created.");
		}
	}
}
