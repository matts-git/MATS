/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  SubtractionProblemTest.java
 *
JUnit tests for fields and methods of concrete class SubtractionProblem, a nested subclass of Problem.
*/


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Project.Problem;

//Nested subclass of Problem
class SubtractionProblemTest {

	// Postcondition: SubtractionProblem.calculateBonus() is tested using Problem.difficulty assigned to 10
	@Test
	void testCalculateBonus() {
		Problem testParg = new Problem.SubtractionProblem();
		testParg.setDifficulty(10);
		assertEquals(testParg.calculateBonus(), 250);
	}

	// Postcondition: SubtractionProblem.solutionCalculator() is tested using the assigned int array
	@Test
	void testSolutionCalculator() {
		Problem testParg = new Problem.SubtractionProblem();
		int testArray [] = {5, 2, 1};
		assertEquals(testParg.solutionCalculator(testArray), 3);
	}

	// Trivial default constructor test
	@Test
	void testSubtractionProblem() {
		Problem testParg = new Problem.SubtractionProblem();
		assertNotNull(testParg);
	}

	// Trivial constructor with arguments test
	@Test
	void testSubtractionProblemIntStringIntInt() {
		Problem testParg = new Problem.SubtractionProblem (12);
		assertNotNull(testParg);
	}

	// Postcondition: SubtractionProblem.additionEquation() is tested using the assigned int array
	@Test
	void testGenerateEquation() {
		Problem testParg = new Problem.SubtractionProblem();
		int testArray [] = {1, 1, 1};
		assertEquals(testParg.generateEquation(testArray), "\n1 - 1 = ");
	}

}
