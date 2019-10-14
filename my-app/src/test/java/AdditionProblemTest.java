/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  AdditionProblemTest.java
 *
JUnit tests for fields and methods of concrete class AdditionProblem, a nested subclass of Problem.
*/


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Project.Problem;

//Nested subclass of Problem
class AdditionProblemTest {

	// Postcondition: AdditionProblem.solutionCalculator() is tested using the assigned int array
	@Test
	void testSolutionCalculator() {
		Problem testParg = new Problem.AdditionProblem(2);
		int testArray [] = {1, 1, 1};
		assertEquals(testParg.solutionCalculator(testArray), 2);
	}

	// Trivial default constructor test
	@Test
	void testAdditionProblem() {
		Problem testParg = new Problem.AdditionProblem();
		assertNotNull(testParg);
	}

	// Trivial constructor with arguments test
	@Test
	void testAdditionProblemIntStringIntInt() {
		Problem testParg = new Problem.AdditionProblem(2);
		assertNotNull(testParg);
	}

	// Postcondition: AdditionProblem.additionEquation() is tested using the assigned int array
	@Test
	void testGenerateAdditionEquation() {
		Problem testParg = new Problem.AdditionProblem(2);
		int testArray [] = {1, 1, 1};
		assertEquals(testParg.generateEquation(testArray), "\n1 + 1 = ");
	}

}
