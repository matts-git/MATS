/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  DivisionProblemTest.java
 *
JUnit tests for fields and methods of concrete class DivisionProblem, a nested subclass of Problem.
*/


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import app.Problem;

import org.junit.Ignore;
import org.junit.Test;


//Nested subclass of Problem
public class DivisionProblemTest {

	// Postcondition: DivivionProblem.calculateBonus() is tested using Problem.difficulty assigned to 5
	@Test
	public void testCalculateBonus() {
		Problem testParg = new Problem.DivisionProblem();
		testParg.setDifficulty(5);
		assertEquals(testParg.calculateBonus(), 100);
	}

	// Postcondition: DivisionProblem.solutionCalculator() is tested using the assigned int array
	@Test
	public void testSolutionCalculator() {
		Problem testParg = new Problem.DivisionProblem();
		int testArray [] = {10, 2, 1};
		assertEquals(testParg.solutionCalculator(testArray), 5);
	}

	// Trivial default constructor test
	@Test
	public void testDivisionProblem() {
		Problem testParg = new Problem.DivisionProblem();
		assertNotNull(testParg);
	}

	// Trivial constructor with arguments test
	@Test
	public void testDivisionProblemIntStringIntInt() {
		Problem testParg = new Problem.DivisionProblem(5);
		assertNotNull(testParg);
	}

	// Postcondition: DivisionProblem.additionEquation() is tested using the assigned int array
	@Test
	public void testGenerateEquation() {
		Problem  testParg = new Problem.DivisionProblem();
		int testArray [] = {10, 2, 1};
		assertEquals(testParg.generateEquation(testArray), "\n10 / 2 = ");
	}

	@Ignore ("Using non-JUnit test for this due to File IO operations")
	public void testSetNumArray() {
		fail("Not yet implemented");
	}
}
