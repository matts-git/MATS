/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  MultiplicationProblemTest.java
 *
JUnit tests for fields and methods of concrete class MultiplicationProblem, a nested subclass of Problem.
*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import app.Problem;

import org.junit.Ignore;
import org.junit.Test;

//Nested subclass of Problem
public class MultiplicationProblemTest {

	// Postcondition: MultiplicationProblem.calculateBonus() is tested using Problem.difficulty assigned to 5
	@Test
	public void testCalculateBonus() {
		Problem testParg = new Problem.MultiplicationProblem();
		testParg.setDifficulty(5);
		assertEquals(testParg.calculateBonus(), 150);
	}

	// Postcondition: MultiplicationProblem.solutionCalculator() is tested using the assigned int array
	@Test
	public void testSolutionCalculator() {
		Problem testParg = new Problem.MultiplicationProblem();
		int testArray [] = {5, 2, 1};
		assertEquals(testParg.solutionCalculator(testArray), 10);
	}

	// Trivial default constructor test
	@Test
	public void testMultiplicationProblem() {
		Problem testParg = new Problem.MultiplicationProblem();
		assertNotNull(testParg);
	}

	// Trivial constructor with arguments test
	@Test
	public void testMultiplicationProblemIntStringIntInt() {
		Problem testParg = new Problem.MultiplicationProblem(10);
		assertNotNull(testParg);
	}

	// Postcondition: MultiplicationProblem.additionEquation() is tested using the assigned int array
	@Test
	public void testGenerateEquation() {
		Problem testParg = new Problem.MultiplicationProblem(10);
		int testArray [] = {1, 1, 1};
		assertEquals(testParg.generateEquation(testArray), "\n1 * 1 = ");
	}

	@Ignore ("Using non-JUnit test for this due to File IO operations")
	public void testSetNumArray() {
		fail("Not yet implemented");
	}
}
