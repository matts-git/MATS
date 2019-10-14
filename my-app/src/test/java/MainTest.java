/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  MainTest.java
 *
JUnit tests for fields and methods of class 'Main'. Theses tests walk through the function calls made within the relevant methods. This design is
necessitate by multiple calls for user input through the execution of these methods in the MATS program. Non-JUnit tests for methods in this class 
are included in MainNoJunitTest.java, which are perhaps more relevant to actual run-time execution.
*/


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
//import org.junit.jupiter.api.Test;

class MainTest {

	@Ignore ("Main method return type void")
	void testMain() {
		fail("Not yet implemented");
	}
	
}
