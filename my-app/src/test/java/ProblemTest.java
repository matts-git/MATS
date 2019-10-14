/** Matthew Schuckmann
 *	matts.git@gmail.com
 *  ProblemTest.java
 *
JUnit tests for fields and methods of abstract superclass Problem. These are mostly trivial, with more testing of more 
sophisticated methods sub-contracted out to to GenericQuizTestNoJunit.
*/


import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import Project.Problem;

public class ProblemTest {

	Problem testP = new Problem.AdditionProblem();
	
	@Test
	public void testProblem() {
		assertNotNull(testP);
	}

	@Test
	public void testProblemIntStringIntInt() {
		Problem testParg = new Problem.AdditionProblem(2);
		assertNotNull(testParg);
	}

	@Test
	public void testgetDifficulty() {
		int diff = 2;
		Problem testParg = new Problem.AdditionProblem();
		testParg.setDifficulty(diff);
		assertEquals(diff, testParg.getDifficulty());
	}

	@Test
	public void testSetDifficulty() {
		Problem testParg = new Problem.AdditionProblem(2);
		testParg.setDifficulty(12);
		assertEquals(12, testParg.getDifficulty());
	}

	@Test
	public void testGetAnswer() {
		Problem testParg = new Problem.AdditionProblem();
		testParg.setAnswer(12);
		assertEquals(12, testParg.getAnswer());
	}

	@Test
	public void testSetAnswer() {
		Problem testParg = new Problem.AdditionProblem();
		testParg.setAnswer(12);
		assertEquals(12, testParg.getAnswer());
	}

	@Test
	public void testGetEquation() {
		Problem testParg = new Problem.AdditionProblem();
		testParg.setEquation("2 + 2 = ");
		assertEquals(testParg.getEquation(), "2 + 2 = ");
	}

	@Test
	public void testSetEquation() {
		Problem testParg = new Problem.AdditionProblem();
		testParg.setEquation("2 + 2 = ");
		assertEquals(testParg.getEquation(), "2 + 2 = ");
	}

	@Test
	public void testGetSolution() {
		Problem testParg = new Problem.AdditionProblem(0);
		assertEquals(testParg.getSolution(), 0);
	}

	@Test
	public void testSetSolution() {
		Problem testParg = new Problem.AdditionProblem();
		testParg.setSolution(1);
		assertEquals(testParg.getSolution(), 1);
	}

	@Test
	public void testGetQuizType() {
		Problem testParg = new Problem.AdditionProblem();
		assertEquals(testParg.getQuizType(), "Addition");
	}

	@Test
	public void testSetQuizType() {
		Problem testParg = new Problem.AdditionProblem();
		testParg.setQuizType("test type");
		assertEquals(testParg.getQuizType(), "test type");
	}

	@Test
	public void testEvaluateCorrectness() {
		Problem testParg = new Problem.AdditionProblem();
		testParg.setSolution(12);
		testParg.setAnswer(12);
		assertTrue(testParg.evaluateCorrectness());
	}

	@Test
	public void testCalculateBonus() {
		Problem testParg = new Problem.AdditionProblem();
		testParg.setDifficulty(12);
		assertEquals(testParg.calculateBonus(), 120);
	}

	@Ignore ("Abstract method")
	@Test
	public void testSolutionCalculator() {
		fail("Not yet implemented");
	}
	
	@Ignore ("Abstract method")
	@Test
	public void testGenerateEquation() {
		fail("Not yet implemented");
	}
	
	@Ignore ("Using non-JUnit test GenericQuizTestNoJunit for this due to File IO operations")
	@Test
	public void testDiffInput() {
		fail("Not yet implemented");
	}

	@Ignore ("Using non-JUnit test GenericQuizTestNoJunit for this due to File IO operations")
	@Test
	public void testSetNumArray() {
		fail("Not yet implemented");
	}

	@Ignore ("Using non-JUnit test GenericQuizTestNoJunit for this due to File IO operations")
	@Test
	public void testAnswerInput() {
		fail("Not yet implemented");
	}

}
