/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  GenericQuizTest.java
 *
JUnit tests for class GenericQuiz methods quizMenu() and quizMaker(). Theses tests walk through the function calls made within the  
method. This design is necessitated by multiple calls for user input through the execution of these methods in the MATS program. Non-JUnit tests 
method. This for methods in this class are included in GenericQuizNoJunit.java, which are perhaps more relevant to actual run-time execution.
*/


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import Project.Problem;

public class GenericQuizTest {

	// A non-JUnit test of this method can be performed using GenericQuizNoJunit.java 
	// Postcondition: sequential execution of function calls for GenericQuiz class method quizMaker().
	@Test
	public void testQuizMaker() {
		Problem testP = new Problem.AdditionProblem();
		testP.setDifficulty(1);
		int testArray[] = {2, 2, 1};
		testP.setArray(testArray);
		testP.generateEquation(testArray);
		testP.solutionCalculator(testArray);
		testP.setAnswer(4);
		testP.evaluateCorrectness();
		assertEquals(testP.generateEquation(testArray), "\n2 + 2 = ");
		assertEquals(testP.solutionCalculator(testArray), 4);
		assertTrue(testP.evaluateCorrectness());
		assertEquals(testP.calculateBonus(), 10);
	}

}
