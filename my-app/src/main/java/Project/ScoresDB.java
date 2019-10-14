/**
 * Matthew Schuckmann
 * matts.git@gmail.com
 * GenericQuiz.java 
 * 
This class 'ScoresDB' contains fields and methods used to interact with JDBMS Derby databases for the purpose of tracking high scores
and average answer times and comparing said values between users.
 */

package Project;

import java.sql.*;
import java.util.LinkedHashMap;

public class ScoresDB {
	
	// Class fields, which are all set by GenericQuiz methods
	static String userName;									
	static LinkedHashMap <String, Integer> recordMap;		
	static Boolean isNewUser = false;						
	static String quizType;									
	static Double aveTime;									
	
	// Default constructor
	public ScoresDB() {
	}
		
	// Setters and getters
	public String getUserName() {
		return userName;
	}
	public static void setUserName(String aName) {
		userName = aName;
	}
	public static Boolean getIsNewUser() {
		return isNewUser;
	}
	public static void setIsNewUser(Boolean isNewUser) {
		ScoresDB.isNewUser = isNewUser;
	}
	public LinkedHashMap<String, Integer> getRecordMap() {
		return recordMap;
	}
	public static void setRecordMap(LinkedHashMap<String, Integer> aRecordMap) {
		recordMap = aRecordMap;
	}
	public static String getQuizType() {
		return quizType;
	}
	public static void setQuizType(String quizType) {
		ScoresDB.quizType = quizType;
	}
	public static Double getAveTime() {
		return aveTime;
	}
	public static void setAveTime(Double aveTime) {
		ScoresDB.aveTime = aveTime;
	}
	
	// Precondition: Class fields have been set by calls made from GenericQuiz.HighScore.
	// Postcondition1: The user has set high score and/or time records and the Derby database 'scorerecords' tables 
	// 'users' and 'averagetimes' are updated accordingly with sorted records output to the console.
	// Postcondition2: No records have been broken and sorted records are output to the console.
	public static void main(String[] args) {
		
		// Instantiate string for accessing 'averagetimes' columns
		String timeType = (quizType + "time");
		
		try {
			
			// Instantiate connection to embedded database using file path specified by Michael Huang in live classroom on 2019-6-14
			Connection conn = DriverManager.getConnection("jdbc:derby:scorerecords", "matt", "nopasswd");
			System.out.println("Database now connected.");
			
			// Conditional statement handling the case of a previous user where existing records are selected and updated
			if (!isNewUser) {

				// Prepared statement intended to update table 'averagetimes' with quiz average answer time when a new 
				// record is achieved or it is the first time this quiz type has been completed. 
				String timeString = ("UPDATE averagetimes SET " + timeType + " = ? WHERE playername = ? AND " + timeType +
						" > " + aveTime + " OR playername = ? AND " + timeType + " = 0");
				PreparedStatement timeStmt = conn.prepareStatement(timeString);
				timeStmt.setDouble(1, aveTime);
				timeStmt.setString(2, userName);
				timeStmt.setString(3, userName);
				timeStmt.executeUpdate();			
				
				// Lambda expression on stream of user score record linked hash map intended to update specific user record
				// with stored score records. This linked hash map was assigned from the map created by
				// GenericQuiz.HighScore.recordWriter()
				recordMap.entrySet().stream().forEach(e ->  {
					try {
						String prepString = ("UPDATE users SET " + e.getKey() + " = ? WHERE name = ?");
						PreparedStatement preparedStmt = conn.prepareStatement(prepString);
						preparedStmt.setInt(1, e.getValue());
						preparedStmt.setString(2, userName);
						preparedStmt.executeUpdate();
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				});
			}
			
			// Conditional statement handling the case of a new user where new records must be inserted into tables 'users'
			// and 'averagetimes'. Table 'averagetimes' is then updated.
			else {
				
				// Insertion of records from linked hash map into table 'users' storing high scores. Because these values
				// have been updated by GenericQuiz.HighScore.recordWriter(), they need not be updated here.
				String prepString = ("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)");
				PreparedStatement preparedStmt = conn.prepareStatement(prepString);
				preparedStmt.setString(1, userName);
				preparedStmt.setInt(2, recordMap.get("Addition"));
				preparedStmt.setInt(3, recordMap.get("Subtraction"));
				preparedStmt.setInt(4, recordMap.get("Multiplication"));
				preparedStmt.setInt(5, recordMap.get("Division"));
				preparedStmt.setInt(6, recordMap.get("Lifetime"));
				preparedStmt.executeUpdate();
				
				// Insertion of new records into table 'averagetimes' storing average answer time records
				String timeString = String.format("INSERT INTO averagetimes VALUES (?, 0.0, 0.0, 0.0, 0.0)");
				PreparedStatement timeStmt = conn.prepareStatement(timeString);
				timeStmt.setString(1, userName);
				timeStmt.executeUpdate();
				
				// Update of relevant column in table 'averagetimes'
				String timeString2 = ("UPDATE averagetimes SET " + timeType + " = ? WHERE playername = ?");
				PreparedStatement timeStmt2 = conn.prepareStatement(timeString2);
				timeStmt2.setDouble(1, aveTime);
				timeStmt2.setString(2, userName);
				timeStmt2.executeUpdate();
			}
			
			System.out.println("\nHigh Score Rankings for " + quizType + " Quiz.\n");
			
			// Multi-table join query intended to select relevant high score records from table 'users' sorted in descending order 
			// along with corresponding average answer time from table 'averagetimes'
			String reportString = ("SELECT name, " + quizType + " , " + timeType + " FROM users, averagetimes WHERE users.name = averagetimes.playername AND " 
			+ quizType + " > 0" + " ORDER BY " + quizType + " DESC");
			PreparedStatement reportStmt = conn.prepareStatement(reportString);
			ResultSet reportResult = reportStmt.executeQuery();
			
			// Metadata object instantiation to provide parameters for outputting database tables to console
			ResultSetMetaData metaReport = reportResult.getMetaData();
			
			// Loop intended to output formatted table 'users' column names to console
		    for (int i = 1; i <= metaReport.getColumnCount(); i++) {
	           System.out.printf("%-20s", metaReport.getColumnName(i));
		    }
		    
		    System.out.println();
		    
		    // Loop intended to output formatted table 'users' records to console 
		    while (reportResult.next()) {
	    	   for (int i = 1; i <= metaReport.getColumnCount(); i++) {
	    		   System.out.printf("%-20s", reportResult.getObject(i));
	    	   }
	           System.out.println();
		    }
		    
		    // Output user's quiz average time taken to console
		    System.out.println("\nAverage Time for this quiz: " + aveTime + "\n");
			
			System.out.println("\nAverage Time Rankings for " + quizType + " Quiz.\n");
			
			// Multi-table join query intended to select relevant average time records from table 'averagetimes' corresponding to 
			// table 'users' column 'name', sorted in ascending order.
			String reportString2 = ("SELECT name, " + timeType + " FROM users, averagetimes WHERE users.name = averagetimes.playername" + 
			" AND users." + quizType + " > 0" + " ORDER BY " + timeType);			
			PreparedStatement reportStmt2 = conn.prepareStatement(reportString2);
			ResultSet reportResult2 = reportStmt2.executeQuery();
			
			// Metadata object instantiation to provide parameters for outputting database tables to console
			ResultSetMetaData metaReport2 = reportResult2.getMetaData();			       
		    
			// Loop intended to output formatted table 'averagetimes' relevant column names to console
			for (int i = 1; i <= metaReport2.getColumnCount(); i++) {
	           System.out.printf("%-20s", metaReport2.getColumnName(i));
		    }
		    
		    System.out.println();
		    
		    // Loop intended to output formatted table 'averagetimes' records to console  
		    while (reportResult2.next()) {
		    	for (int i = 1; i <= metaReport2.getColumnCount(); i++) {
		    		System.out.printf("%-20s", reportResult2.getObject(i));
		    	}
		    	System.out.println();
		    }
		    
		    // Close the database connection 
		    conn.close();
		    System.out.println("\nDatabase now disconnected.");
		}

		catch (SQLException sqlex)  {
			
			// Sanity check to assist debugging
			System.out.println(userName);
			
			System.out.println("\nSQL exception encountered.\n");
			sqlex.printStackTrace();
		
		}
	}

}
