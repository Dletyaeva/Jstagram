package jst;
import java.sql.*;
import java.util.*;


//create connection with sql

//display the pre-init database values

//main function 
	//Main window -> init function: starts up and preloads all the data
	// login function: 

	//every time the user is in post window function displays everything in the database plus the updated stuff

public class Main {
	public final static String hostname = "cse-linux-01.unl.edu";
	public final static String username = "dletyaeva2"; // your database username
	public final static String password = "Xei1eipheeC1"; // your database password
	public final static String url = "jdbc:mysql://"+hostname+"/"+username;
	
	public static void main (String[] args) {
		loadData();

	}
	
	
	public static void  loadData() {
		// 1. create a database connection
	    Connection conn = null;
	    try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    
	    // 2. prepare and execute statement
	    String s = "select postText, userName, postTime from PostDetails natural inner join UserDetails";
	    PreparedStatement prep = null;
	    ResultSet rs = null;
	    try {
			prep = conn.prepareStatement(s);
			
			rs = prep.executeQuery();
			
		    System.out.println("Beginning of query result");
			while(rs.next()) {
				String userName = rs.getString("userName");
				String postTime = rs.getString("postTime");
				
				// Student s = new Student(studentID, studentName, studentYear, studentMajor);
				System.out.println(userName+"\t"+postTime);
			}
		    System.out.println("End of query result");
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 3. close all resources
	    // do not combine with step 2
	    // otherwise, resources are not closed when something wrong at step 2
	    try {
			if (rs != null) 
				rs.close();
			if (prep != null)
				prep.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
}
}