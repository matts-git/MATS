package nonJunitTests;
/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  MainNoJunitTest.java
 *
Non-JUnit tests for fields and methods of class 'Main'. This tests the main menu function.
*/


import Project.Main;

public class MainNoJunitTest {
	
	// Intent: This non-JUnit method tests the functionality of MATS Main class method quizMainMenu(). This methods depend repeatedly upon
	// user and file I/O, thus necessitating non-JUnit testing.
	public static void main(String[] args) {
		quizMenuTest();
	}

	// Precondition: user enters input 
	// Postcondition1: a functional quiz menu is presented to the user, a quiz is taken, and the system outputs "Successful Test"
	// Postcondition2: the method call results in an Exception, the system outputs "Exception in Main.quizMenu()" followed by a stack trace.
	public static void quizMenuTest() {
		try {
			Main.main(null);
		}
		catch(Exception e) {
			System.out.println(e + "Exception in Main.quizMenu()"); 
		    e.printStackTrace(System.out);
		}		
		System.out.println("\nSuccessful Test.");
	}
}
