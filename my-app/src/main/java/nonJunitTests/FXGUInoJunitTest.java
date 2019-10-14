package nonJunitTests;
/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  FXGUInoJunitTest.java
 *
Non-JUnit tests for JavaFX GUI user menu. 
*/


import Project.FXGUI;

public class FXGUInoJunitTest {
	
	public static void main(String[] args) {

		try {
			FXGUI.main(null);
			System.out.println("JavaFX GUI test success.");
		}
		catch (Exception e) {
			System.out.println("JavaFX GUI test failed.");
			e.printStackTrace();
		}
	}
	
}
