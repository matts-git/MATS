/**
 * Matthew Schuckmann
 * matts.git@gmail.com
 * Main.java 
 * 
This class 'Main' contains the main() method for the CS622 project MATS (mathematical acuity testing system) program.
It simply calls a method from the class FXGUI which provides the user quiz menu.
 */

package app;

	public class Main {
	
		// Main menu functionality provided by call to GUI class method
		public static void main(String[] args) throws InterruptedException {
			
			FXGUI.main(null);
		}
}
