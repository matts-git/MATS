/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  Problem.java
 *
This class, Problem.java, is the superclass of nested concrete subclasses AdditionProblem, SubtractionProblem,
MultiplicationProblem, and DivisionProblem.

These classes contain fields and methods used to generate objects representing arithmetic quiz problems.
*/

package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Problem {
	
	// Superclass fields
	protected int difficulty;
	protected String equation;
	protected int solution;
	protected int answer;
	protected int numArray [] = new int[2];
	public String quizType;
	protected int score;
		
	// Default superclass constructor signature
	public Problem() {
	}
	
	// Superclass constructor signature with arguments
	public Problem(int aDifficulty, String anEquation, int aSolution, int anAnswer) {
		this.difficulty = aDifficulty;
		this.equation = anEquation;
		this.solution = aSolution;
		this.answer = anAnswer;	
	}
	
	// Getter and setter methods for superclass fields
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}	
	public String getEquation() {
		return equation;
	}
	public void setEquation(String equation) {
		this.equation = equation;
	}
	public int getSolution() {
		return solution;
	}	
	public void setSolution(int solution) {
		this.solution = solution;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public void setArray(int [] numArray) {
		this.numArray = numArray;
	}
	public int[] getNumArray() {
		return numArray;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setQuizType(String aType) {
		this.quizType = aType;
	}
	public String getQuizType() {
		return quizType;
	}
	
	
	// Intent: Superclass method for generating integer constants for subclasses. The magnitude of numbers is controlled by   
	// the user-selected difficulty level and a default factor of 100. Polymorphism is applied by other subclasses of Problem 
	// which override setNumArray() to apply different magnitude factors. 
	// Precondition: 'difficulty' has been assigned to an integer
	// Postcondition: An integer array 'numnArray' is returned containing random integers of factor-controlled magnitude
	
	public int [] setNumArray () {
		int factor = 100;
		numArray[0] = (int)(Math.random() * (this.difficulty * factor));
		numArray[1] = (int)(Math.random() * (this.difficulty * factor));
		return numArray;
	}
	
	/**
	 * Note: I have opted not to close this scanner object due the subsequent ' java.util.NoSuchElementException' which occurs
	 * when the method answerInput() is sequentially called. This is because closing the scanner object also closes the System.in 
	 * input stream as well. As I understand, this should not cause a memory leak since System.in is closed by the JVM and you 
	 * should not necessarily need to close it. Instead, the resource warning has been suppressed.
	 */
	
	// Intent: Superclass method for requesting and taking user answer input.
	// Precondition: 'answerScan' is of type integer
	// Postcondition: 'answer' is assigned to integer 'answerScan' and answer is returned
	@SuppressWarnings("resource")
	public int answerInput () {
		System.out.println("Please enter your answer: ");
		
		// Exception handling for invalid user input
		try {
			Scanner answerScan = new Scanner(System.in);
			answer = answerScan.nextInt();
		}
		catch(InputMismatchException ex) {
			System.out.println("Input must be an integer. Automatic fail.");
			//ex.printStackTrace();
		}
		//answerScan.close();
		this.setAnswer(answer);
		return answer;
	}
	
	// Intent: Superclass method for evaluating whether the user's answer matches the actual solution.
	// Precondition: setSolution() and setAnswer() have assigned 'solution' and 'answer', respectively, to integers
	// Postcondition : 'solution' is equal to 'answer' and true is returned OR 'solution' is not equal to 'answer' and false is returned
	public boolean evaluateCorrectness() {
		if (getSolution() != getAnswer()) {return false;}
		else {return true;}
	}
	
	// Intent: Superclass method for calculating a score bonus based upon difficulty level and a default multiplier of 10. Several 
	// subclasses override this method to apply varying score multipliers.
	// Precondition: 'difficulty' had been assigned to an integer and evaluateCorrectness() has returned true
	// Postcondition: 'score' is assigned to an integer equal to 10 times the value of 'difficulty' and 'score' is returned
	public int calculateBonus () {
		score  = 10 * difficulty;
		return score;
	}
	
	// Abstract methods devoid of body provided to enforce inclusion of such method in all subclasses
	public abstract int solutionCalculator(int[] numArray); 
	public abstract String generateEquation(int [] numArray); 
	
	/**
	 * Beginning of nested concrete subclasses 
	 */
	 
	public static class AdditionProblem extends Problem {
		// Fields unique to this subclass
		public int sum;
		public String additionEquation;
		public String quizType = "Addition";

		public void setQuizType(String aType) {
			quizType = aType;
		}
		public String getQuizType () {
			return quizType;
		}

		// Default subclass constructor signature
		public AdditionProblem() {
		}
		
		// Subclass constructor signature with arguments. Body is empty since a programmer-written super() isn't 
		// required because superclass Problem contains a default constructor which gets invoked automatically here.
		public AdditionProblem(int addDifficulty) {
			this.difficulty = addDifficulty;
		}	
		
		// Intent: Method for generating displayed equation. Passes returned string to the superclass method setEquation()
		// Precondition: An integer array has been created through the inherited superclass method setNumArray()
		// Postcondition: The difficulty level and a corresponding addition equation are printed to console
		public String generateEquation(int [] numArray) {
			additionEquation = ("\n" + numArray[0] + " + " + numArray[1] + " = ");
			super.setEquation(additionEquation);
			System.out.println(additionEquation);
			return additionEquation;
		}
		
		// Intent: Method for calculating solution. Passes returned value to the superclass method setSolution()
		// Precondition: An integer array has been created through the inherited superclass method setNumArray()
		// Postcondition: 'sum' is assigned to the sum of the integers contained in the first two indices of the integer
		// array. The value of 'sum' is then passed to in a call to superclass method setSolution(). 
		@Override
		public int solutionCalculator(int[] numArray) {
			// TODO Auto-generated method stub
			sum = (numArray[0] + numArray[1]);
			super.setSolution(sum);
			return sum;
		}
	}
	
	public static class SubtractionProblem extends Problem {

		// Fields unique to this subclass
		public int difference;
		public String subtractionEquation;
		public String quizType = "Subtraction";
		
		public void setQuizType(String aType) {
			quizType = aType;
		}
		public String getQuizType () {
			return quizType;
		}
		
		// Default subclass constructor signature
		public SubtractionProblem() {
		}

		// Subclass constructor signature with arguments. Body is empty since a programmer-written super() isn't 
		// required because superclass Problem contains a default constructor which gets invoked automatically here.
		public SubtractionProblem(int subDifficulty) {
			this.difficulty = subDifficulty;
		}
		
		// Intent: Method for generating displayed equation. Passes returned string to the superclass method setEquation()
		// Precondition: An integer array has been created through the inherited superclass method setNumArray()
		// Postcondition: The difficulty level and a corresponding subtraction equation are printed to console
		public String generateEquation(int [] numArray) {
			subtractionEquation = ("\n" + numArray[0] + " - " + numArray[1] + " = ");
			super.setEquation(subtractionEquation);
			System.out.println(subtractionEquation);
			return subtractionEquation;
		}
		
		// Intent: Polymorphic concrete implementation of superclass abstract method for calculating solution.
		// Precondition: an integer array has been created through the inherited superclass method setNumArray()
		// Postcondition: 'difference' is assigned to the difference of the integers contained in the first two indices of the integer array. 
		// The value of 'difference' is then passed in a call to the superclass method setSolution().
		//@Override
		public int solutionCalculator(int[] numArray) {
			difference = (numArray[0] - numArray[1]);
			super.setSolution(difference);
			return difference;
		}
		
		// Intent: Override superclass method to apply a different score multiplier.
		// Precondition: 'difficulty' had been assigned to an integer and evaluateCorrectness() has returned true
		// Postcondition: 'score' is assigned to an integer equal to 15 times the value of 'difficulty' and 'score' is returned
		@Override
		public int calculateBonus () {
			int score  = 25 * difficulty;
			return score;
		}
	}
	
	public static class MultiplicationProblem extends Problem {

		// Fields unique to this subclass
		public int product;
		public String multiplicationEquation;
		public String quizType = "Multiplication";

		
		public void setQuizType(String aType) {
			quizType = aType;
		}
		public String getQuizType () {
			return quizType;
		}
		
		// Default subclass constructor signature
		public MultiplicationProblem() {
		}

		// Subclass constructor signature with arguments. Body is empty since a programmer-written super() isn't 
		// required because superclass Problem contains a default constructor which gets invoked automatically here.
		public MultiplicationProblem(int multDifficulty) {
			this.difficulty = multDifficulty;
		}

		// Intent: Method for generating displayed equation. Passes returned string to the superclass method setEquation()
		// Precondition: An integer array has been created through the inherited superclass method setNumArray()
		// Postcondition: The difficulty level and a corresponding multiplication equation are printed to console
		@Override
		public String generateEquation(int [] numArray) {
			multiplicationEquation = ("\n" + numArray[0] + " * " + numArray[1] + " = ");
			super.setEquation(multiplicationEquation);
			System.out.println(multiplicationEquation);
			return multiplicationEquation;
		}
		
		// Intent: Override superclass method to apply a differetn difficulty factor. 
		// Precondition: 'difficulty' has been assigned to an integer
		// Postcondition: An integer array 'numnArray' is returned containing random integers 
		@Override
		public int [] setNumArray () { 
			int factor = 10;
			numArray[0] = (int)(Math.random() * (this.difficulty * factor));
			numArray[1] = (int)(Math.random() * (this.difficulty * factor));
			return numArray;
		}

		// Intent: Method for calculating solution. 
		// Precondition: An integer array has been created through the polymorphic subclass method setNumArray()
		// Postcondition: 'product' is assigned to the product of the integers contained in the first two indices of the integer array. 
		// The value of 'product' is then passed in a call to superclass method setSolution().
		//@Override
		public int solutionCalculator(int[] numArray) {
			product = (numArray[0] * numArray[1]);
			super.setSolution(product);
			return product;
		}
		
		// Intent: Overrides superclass method to apply a different score multiplier.
		// Precondition: 'difficulty' had been assigned to an integer and evaluateCorrectness() has returned true
		// Postcondition: 'score' is assigned to an integer equal to 30 times the value of 'difficulty' and 'score' is returned
		@Override
		public int calculateBonus () {
			int score  = 30 * difficulty;
			return score;
		}	
	}
		
	public static class DivisionProblem extends Problem {

		// Fields unique to this subclass
		public int quotient;
		public String divisionEquation;
		public String quizType = "Division";
		
		
		public void setQuizType(String aType) {
			quizType = aType;
		}
		public String getQuizType () {
			return quizType;
		}
		
		// Default subclass constructor signature
		public DivisionProblem() {
		}

		// Subclass constructor signature with arguments. Body is empty since a programmer-written super() isn't 
		// required because superclass Problem contains a default constructor which gets invoked automatically here.
		public DivisionProblem(int divDifficulty) {
			this.difficulty = divDifficulty;
		}
			
		// Intent: Method for generating displayed equation. Passes returned string to the superclass method setEquation()	
		// Precondition: An integer array has been created through method setNumArray()
		// Postcondition: The difficulty level and a corresponding division equation are printed to console
		public String generateEquation(int [] numArray) {
			divisionEquation = ("\n" + numArray[0] + " / " + numArray[1] + " = ");
			super.setEquation(divisionEquation);
			System.out.println(divisionEquation);
			return divisionEquation;
		}
		
		// Intent: Override superclass method to apply a different difficulty factor and avoid 0's from being included in numArray[]
		// Precondition:'difficulty' has been assigned to an integer
		// Postcondition: An integer array 'numnArray' is returned, with the first two indices containing random integers such that 
		// the quotient of the first (numerator) and second (denominator) indices is an integer with no remainder. numArray[] is returned.
		@Override
		public int [] setNumArray () {
			int factor = 100;
			numArray[0] =  (int)(Math.random() * (this.difficulty * factor)) + 1;
			numArray[1] =  (int)(Math.random() * (this.difficulty * factor)) + 1;
			
			// Loop through pairs of constants until a division problem with an integer solution is achieved.
			int tempQuotient = numArray[0] % numArray[1];
			while (tempQuotient != 0) {
				numArray[0] = (int)(Math.random() * (difficulty * 100)) + 1;
				numArray[1] = (int)(Math.random() * (difficulty * 100)) + 1;
				tempQuotient = numArray[0] % numArray[1];
			}
			return numArray;
		}
		
		// Intent: Polymorphic concrete implementation of superclass abstract method for calculating solution.
		// Precondition: An integer array has been created through the polymorphic subclass method setNumArray()
		// Postcondition: 'quotient' is assigned to the quotient of the integers contained in the first two indices of the integer array. 
		// The value of 'quotient' is then passed in a call to superclass method setSolution().
		@Override
		public int solutionCalculator(int[] numArray) {
			quotient = (numArray[0] / numArray[1]);
			super.setSolution(quotient);
			return quotient;
		}
		
		// Intent: Overrides superclass method to apply a different score multiplier.
		// Precondition: 'difficulty' had been assigned to an integer and evaluateCorrectness() has returned true
		// Postcondition: 'score' is assigned to an integer equal to 20 times the value of 'difficulty' and 'score' is returned
		@Override
		public int calculateBonus () {
			int score  = 20 * difficulty;
			return score;
		}	
	}
	
}
	
