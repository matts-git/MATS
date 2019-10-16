/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  ScoresDBNoJunitTest.java
 *
Non-JUnit tests for ScoresDB.java. This class manages JDBMS Derby database interactions, thus necessitating a non-JUnit testing strategy. 
Here, ScoresDB fields are set to test values and a call to the ScoresDB.main() method is executed inside an exception-handling try-catch
clause.
*/

package customTests;

import app.GenericQuiz;
import app.Problem;
import app.ScoresDB;
import app.GenericQuiz.HighScore;
import java.util.LinkedHashMap;

public class ScoresDBNoJunitTest {

	// Postcondition1: ScoresDB fields are properly set to test values, database connectivity and interactions are successful
	// and an appropriate message is output to the console
	// Postcondition2: an exception is encountered and an error message and stack trace are output to the console
	public static void main(String[] args) {
	
		// Instantiate objects for testing purposes
		Problem testProb = new Problem.AdditionProblem();
		HighScore testScore = new GenericQuiz.HighScore();
		
		// Instantiate a score hash map, update the score value and pass to ScoresDB
		LinkedHashMap<String, Integer> testMap = testScore.defaultScoreMap();
		testMap.put(testProb.getQuizType(), 121212);
		ScoresDB.setRecordMap(testMap);
		
		// Pass additional test value assignments to ScoresDB fields
		ScoresDB.setAveTime(12.12);
		ScoresDB.setQuizType(testProb.getQuizType());
		ScoresDB.setUserName("Tester");
		
		// Exception handling clause 
		try {
			ScoresDB.main(null);
			System.out.println("Derby database test passed.");
		}
		catch (Exception ex) {
			System.out.println("Derby database test failed.");
			ex.printStackTrace();
		}
	}
}
