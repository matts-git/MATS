package nonJunitTests;
/** Matthew Schuckmann
 *  matts.git@gmail.com
 *  HighScoreNoJunitTest.java
 *
Non-JUnit tests for fields and methods of class HighScore which is nested in GenericQuiz. 
*/

import java.io.FileOutputStream;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

import Project.GenericQuiz;

public class HighScoreNoJunitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericQuiz.HighScore testScores = new GenericQuiz.HighScore();
		LinkedHashMap<String, Integer> resetMap = testScores.defaultScoreMap();
		testScores.setScoreMap(resetMap);
		
		//System.out.println(testScores.toString());
		try (ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream("records.dat"));) {

			outfile.writeObject(testScores);
			outfile.close();	
		}
	
		
		catch (Exception ex) {
			System.out.println("Problem resetting high score data, sorry!");
			ex.printStackTrace();
		}
		System.out.println("Score file reset successful.");
		//System.out.println(testScores.toString());
	}
	

}
