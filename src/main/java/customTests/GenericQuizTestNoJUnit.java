/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  GenericQuizTest.java
 *
Non-JUnit tests for fields and methods of class GenericQuiz.
*/

package customTests;

import app.GenericQuiz;
import app.Problem;

public class GenericQuizTestNoJUnit {
	
	// Intent: This non-JUnit method tests the functionality of MATS GenericQuiz class. Testing of this class depends repeatedly upon
	// user and file I/O, thus necessitating non-JUnit testing.
	public static void main(String[] args) {
		testGenericQuiz();
	}
	

	
	// Precondition: user must enter integer input for difficulty level
	// Postcondition1: single question addition quiz is presented on console followed by "Test successful".
	// Postcondition2: an exception is encountered and a stack trace is output to console followed by "Test failed".
	private static void testGenericQuiz() {
		
		// General Exception object handling
		try {
			GenericQuiz.Countdown cdTest = new GenericQuiz.Countdown();
			Thread t1 = new Thread(cdTest);
			
			t1.start();
			
			Problem testP;
			testP = new Problem.AdditionProblem();
			GenericQuiz<Problem> testQuiz = new GenericQuiz<Problem>(testP);
			testQuiz.setQuizLength(1);
			testQuiz.setUser("Test name");
			t1.join();
			testQuiz.quizMaker(testP);
			System.out.println("\nquizMaker() test successful.\n");
		}
		catch (Exception ex) {
			System.out.println("\nquizMaker() test failed.\n");
			ex.printStackTrace();
		}
	}

}
