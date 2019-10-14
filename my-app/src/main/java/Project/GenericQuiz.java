/**
 * Matthew Schuckmann
 * matts.git@gmail.com
 * GenericQuiz.java 
 * 
This generic class 'GenericQuiz' contains fields and methods used to generate math assessments.

The nested class HighScore provides fields and methods necessary for updating saved high score record data.
 */

package Project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import CustomExceptions.MalformedHighScoreException;


// Generic class signature wrapping generic type bound to abstract superclass Problem 
public class GenericQuiz <T extends Problem> {
	
	// Class fields
	T t;
	int quizLength = 1;
	int difficulty = 1;
	int selection;
	String user;
	static Double totalTime = 0.0;
	static Boolean timeTrigger = true;
	
	// Default class constructor
	public GenericQuiz() {
	}

	// Class constructor with parameters
	public GenericQuiz(int length, int difficulty, int selection, String user) {
		this.quizLength = length;
		this.difficulty = difficulty;
		this.selection = selection;
		this.user = user;
	}
	
	// Class constructor with generic parameter (bound to Problem or subclass of Problem)
	public GenericQuiz(T aT) {
		this.t = aT;
	}
	
	// Setters and getters
	public T getT() {
		return t;
	}
	public void setT(T aT) {
		this.t = aT;
	}
	public int getQuizLength() {
		return quizLength;
	}
	public void setQuizLength(int quizLength) {
		this.quizLength = quizLength;
	}
	public  int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getSelection() {
		return selection;
	}
	public void setSelection(int selection) {
		this.selection = selection;
	}
	public static Boolean getTimeTrigger() {
		return timeTrigger;
	}
	public static void setTimeTrigger(Boolean timeTrigger) {
		GenericQuiz.timeTrigger = timeTrigger;
	}
	public Double getTotalTime() {
		return totalTime;
	}
	public static void setTotalTime(Double aTotalTime) {
		totalTime = aTotalTime;
	}
	
	// Intent: Nested runnable class for outputting a count-down timer to console for the user and coordinating the 
	// execution of a quiz timer method
	// Precondition: A JavaFX event-driven method has assigned a thread to a Countdown object and called start() on the thread
	// Postcondition1: A count-down timer is output to console, after which start() is called on a Timer thread
	public static class Countdown implements Runnable {
		
		// Instantiate thread object for assignment to a Timer object
		static Thread t2;

		public void run() {
			System.out.println("Quiz starting in 5 seconds");
			try {
				Thread.sleep(2000);
				System.out.println("3...");
				Thread.sleep(1000);
				System.out.println("2..");
				Thread.sleep(1000);
				System.out.println("1.");
				
				// Instantiate Timer object and assign corresponding thread
				Timer timer = new GenericQuiz.Timer();
				t2 = new Thread(timer);
				
				// Start the timer thread
				t2.start();
				
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	// Intent: Nested runnable class for timing the duration of a quiz
	// Precondition: A Countdown object thread has instantiated a Timer object thread and called start() on it
	// Postcondition1: A timer starts after the count-down and outputs the total time taken for the quiz to console
	public static class Timer implements Runnable {
		
		Double timerTime = 0.0;
		long timerEnd;
		Double seconds;
		
		// Assign start time to a long variable
		long timerStart = System.currentTimeMillis();
		
		public void run() {
			
			System.out.println("\n-------------------");
			System.out.println("Timer Thread Begun.");
			System.out.println("-------------------");
					
			synchronized (totalTime) {
			
				// Loop to keep this thread running until the boolean switch timerTrigger is set to false at near
				// the end of the quizMaker() method
				boolean running = true;
			    while(running) {
			    	if (GenericQuiz.getTimeTrigger() == false) {
			    		System.out.println("-------------------");
			    		System.out.println("Timer Thread Ended.");
			    		System.out.println("-------------------");
						
						// Assign end time to a long variable
						timerEnd = System.currentTimeMillis();
						
						// Calculate total time and convert from milliseconds to seconds
						long timerTotal = timerEnd - timerStart;
						seconds = (timerTotal/1000.0);
						
						
						GenericQuiz.setTotalTime(seconds);
						System.out.printf("\nTimer thread measured %.2f seconds\n", seconds);
						running = false;
					}
			        if (Thread.interrupted()) {
			            return;
			        }
			    }
			}	
		}
	}
	
	// Intent: Method for generating subject specific quiz based upon type of generic placeholder passed at instantiation of 
	// GenericQuiz object.
	// Precondition:  A JavaFX event-driven method has called quizMenu() after the user has entered valid GUI menu inputs	
	// Postcondition: A quiz is generated based upon the type of object used to instantiate the generic GenericQuiz class object.
	// public int quizMaker(String user, int length) {
	public int quizMaker(Problem thisProb) throws InterruptedException {
		
		int quizScore = 0;

		// Sanity Check
		// System.out.println("Timer should have started.");
		
		// Loop through question generation
		for (int i = 0; i < quizLength; i++) { 
			
			// Declare integer array variable and assign to return of super class method setNumArray().  
			thisProb.setDifficulty(difficulty);
			
			int tempArray [] = thisProb.setNumArray();
			
			// Calls to superclass methods. May be overridden by corresponding subclass methods.
			thisProb.generateEquation(tempArray);
			thisProb.solutionCalculator(tempArray);
			
			// Start answer timer
			long startTime = System.currentTimeMillis();
			
			// Read scanned answer user input
			thisProb.answerInput();
			
			// End answer timer and calculate time bonus multiplier accordingly
			long endTime = System.currentTimeMillis();
			long timeTook = endTime - startTime;
			long timeBonus = 100000/(timeTook);
			Double seconds = (timeTook/1000.0);
			
			System.out.printf("\nAnswered in %.2f seconds.\n", seconds);
			
			// Conditional statement evaluating the returned value
			if (thisProb.evaluateCorrectness() == true) {
				int bonus = thisProb.calculateBonus();
				long probScore = bonus * timeBonus;
				System.out.printf("Your answer is CORRECT!\nYou earned %d points (%d x %d time bonus)\n\n", probScore, bonus, timeBonus);
				quizScore += probScore;
				}
			else {
				System.out.printf("\nSorry, but your answer is incorrect. The correct answer is %d\n\n", thisProb.getSolution());
			}
		}
		
		// Set boolean to stop Timer() loop and call join() to wait until timer thread is terminated before the next line of 
		// code is processed
		setTimeTrigger(false);
		Countdown.t2.join();
		
		// Pass average answer time to database class
		double aveTime = (totalTime/quizLength);
		ScoresDB.setAveTime(aveTime);
		
		System.out.printf("\nCongratulations %s your total quiz score is %d for this %s quiz. Your average answer time was %.2f seconds\n\n", 
							user, quizScore, thisProb.getQuizType(), aveTime);
		
		// Exception handling of custom MalformedHighScoreException exception thrown during recordWriter() file I/O
		try {
			HighScore.recordWriter(user, thisProb, quizScore);	
		}
		catch (MalformedHighScoreException hs) {
		}
		return quizScore;
	}
	
	
	// Nested class for objects containing a single data structure - LinkedHashMap - which stores high score records. The method 
	// recordWriter() streams HighScore objects to and from a local binary file. Therefore, HighScore implements Serializable.
	public static class HighScore implements Serializable{
		
		// Serial version UID generated by Eclipse required for properly identifying serialized object type
		private static final long serialVersionUID = -2136209512286776528L;
		
		// Class fields
		public static String userName;
		protected LinkedHashMap <String, Integer> scoreMap;
		
		// Default class constructor signature
		public HighScore() {
		}
		// Class constructor signature with arguments
		public HighScore(String aName, LinkedHashMap<String, Integer> aScoreMap) {
			HighScore.userName = aName;
			this.scoreMap = aScoreMap;
		}
		
		// Setter and getter
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			HighScore.userName = userName;
		}
		protected LinkedHashMap<String, Integer> getScoreMap() {
			return scoreMap;
		}
		public void setScoreMap(LinkedHashMap<String, Integer> aScoreMap) {
			this.scoreMap = aScoreMap;
		}
		
		// Default LinkedHashMap score record generator for new users
		public LinkedHashMap<String, Integer> defaultScoreMap () {
			LinkedHashMap <String, Integer> resetScores = new LinkedHashMap<String, Integer>();
			resetScores.put("Addition", 0);
			resetScores.put("Subtraction", 0);
			resetScores.put("Multiplication", 0);
			resetScores.put("Division", 0);
			resetScores.put("Lifetime", 0);
			return resetScores;
		}

		// Intent: Stream HighScore objects from a binary file, update the objects, and write the updated objects back to the binary file
		// Precondition: local records.dat exists and quizMaker() has called this method by passing a Problem object, a user name string,
		// and the quiz score as arguments
		// Postcondition1: Score records are extracted from a map object streamed from records.dat, and the passed score argument is 
		// compared to the appropriate record. If the current score breaks a record, it is announced and records.dat is updated. The life
		// time total accumulated score is always updated. Quiz parameters are passed to Project.ScoresDB.
		// Postcondition2: There is a problem streaming HighScore objects from records.dat and a custom MalformedHighScoreException 
		// is thrown, to be caught by quizMaker().
		public static <T extends Problem> void recordWriter (String name, T newProb, int quizScore) throws MalformedHighScoreException {
			
			// Extract the appropriate key string from the Problem object
			String typeKey = newProb.getQuizType();
			
			// Exception handling which may throw a custom MalformedHighScoreException
			// Stream object data from binary file and assign to variable
			try (ObjectInputStream infile = new ObjectInputStream(new FileInputStream("records.dat"));) {
	
				// Instantiate a HighScore object and assign userName variable
				GenericQuiz.HighScore thisUser = new GenericQuiz.HighScore();
				thisUser.setUserName(name);

				// Casting object streamed in from ObjectInputStream to hash map required at instantiation. Suppressing warning
				// due to direct knowledge of cast validity.
				@SuppressWarnings("unchecked")
				HashMap<String, GenericQuiz.HighScore> scoreHash = (HashMap<String, GenericQuiz.HighScore>)infile.readObject();

				// Search map for existing record. If not found, create an entry
				if (scoreHash.containsKey(name)) {
					thisUser = scoreHash.get(name);
				}
				else {
					
					// Set ScoresDB boolean, later adjusted to allow for consecutive quizzes in the same run
					ScoresDB.setIsNewUser(true);
					System.out.printf("\nNew user detected. Welcome %s!\n", name);
					thisUser.setScoreMap(thisUser.defaultScoreMap());
					scoreHash.put(name, thisUser);
				}
				
				// Instantiate map object and assign to HighScore object score map
				LinkedHashMap<String, Integer> theScores = new LinkedHashMap<>();
				theScores = thisUser.getScoreMap();

				// Declare and assign variables to HighScore object LinkedHashMap values
				int prevLifeTime = theScores.get("Lifetime");
				int scoreRecord = theScores.get(typeKey);
				
				// Compare current score to relevant record and respond
				if (quizScore > scoreRecord) {
					System.out.println("New high score!");
					theScores.put(typeKey, quizScore);
				}
				else if (quizScore == scoreRecord) {
					System.out.println("You've tied your high score!");
				}
				else {
					System.out.println("Sorry, no high score this time.");
				}

				// Life time total is always updated
				int newTotal = prevLifeTime + quizScore;
				theScores.put("Lifetime", newTotal);
				
				// Pass hash map of scores to database class
				ScoresDB.setRecordMap(theScores);
				
				// Output to console the updated high score records
				System.out.printf("\nHigh Score Records for %s\n---------------------------------------\n", name);

				// Stream map entries and pass as arguments to lambda expression
				// Lambda expression prints streamed map entry string keys and int values
				theScores.entrySet().stream().forEach(e ->  System.out.printf("%s\t\t%d\n",e.getKey(), e.getValue()));
				System.out.println("---------------------------------------\n\n");
				
				// Assign updated score map to HighScore object and put into map of users
				thisUser.setScoreMap(theScores);
				scoreHash.put(name, thisUser);
				
				// Stream HashMap output to local binary file
				ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream("records.dat"));
				outfile.writeObject(scoreHash);
				outfile.flush();
				outfile.close();	
			}
			
			// Custom exception thrown and caught by GenericQuiz.quizMaker() when an exception is encountered streaming or updating object data
			catch (Exception ex) {
				ex.printStackTrace();
				throw new MalformedHighScoreException(name);	
			}
			
			// Pass quiz variables to ScoresDB fields for database management
			ScoresDB.setQuizType(typeKey);
			ScoresDB.setUserName(name);
			
			// Call to database management class
			ScoresDB.main(null);
			
			// Set boolean switch to false to enable consecutive quizzes for new users while the program is running
			ScoresDB.setIsNewUser(false);
		}
		
	}
}