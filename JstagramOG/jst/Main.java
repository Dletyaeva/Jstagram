package jst;
import java.sql.*;
import java.util.*;


public class Main {
	public static String CurrentUser = null;
	public static Integer userID = null;
	public static Integer postId = 5;
	public final static Scanner input = new Scanner(System.in);
	
	public static void main (String[] args) {
		mainInterface();				
	}
	
	/*interface at account window page displays options:
	 * go to post window (+)
	 * go to visibility window (V)
	 * logout (L)
	 * quit (Q)
	*/
	public static void homeInterface() {
		Views.accountWindow();
		String userChoice= input.next();
		
		//catch improper user value
		while((userChoice.equals("+") ||  userChoice.equals("V")|| userChoice.equals("L") || userChoice.equals("Q")) == false){
			System.out.println("Enter a valid character please");
			userChoice= input.next();
		}
		if(userChoice.equals("+")) {
			postInterface();
		}
		if(userChoice.equals("V")) {
			vizInterface();
		}
		if(userChoice.equals("L")) {
			logout();
		}
		if(userChoice.equals("Q")) {
			quit();
		}
		
	}
	
	/*interface at post window page displays options:
	 * to make a post (+)
	 * go back to account window(B)
	*/
	public static void postInterface() {
		Views.postWindow();
		String userChoice= input.next();
		
		//catch improper user value
		while((userChoice.equals("+") ||  userChoice.equals("B")) == false){
			System.out.println("Enter a valid character please");
			userChoice= input.next();
		}
		if(userChoice.equals("+")) {
			Post.addTextPost();
		}
		if(userChoice.equals("B")) {
			homeInterface();
		}

	}
	/*interface at post window page displays options:
	 * add user (+)
	 * remove user (-)
	 * go back to account window(B)
	*/
	public static void vizInterface() {
		Views.vizWindow();
		String userChoice= input.next();
		
		//catch improper user value
		while((userChoice.equals("+") ||  userChoice.equals("-") ||userChoice.equals("B")) == false){
			System.out.println("Enter a valid character please");
			userChoice= input.next();
		}
		if(userChoice.equals("+")) {
			Viz.addToViz();
		}
		if(userChoice.equals("-")) {
			Viz.deleteFromViz();	
		}
		if(userChoice.equals("B")) {
			homeInterface();
		}
	}
	
	public static void mainInterface() {
		Views.mainWindow();
		String userChoice= input.next();
		
		//catch improper user value
		while((userChoice.equals("L") ||  userChoice.equals("Q")) == false){
			System.out.println("Enter a valid character please");
			userChoice= input.next();
		}
		if(userChoice.equals("L")) {
			Views.loginWindow();
			login();
		}
		if(userChoice.equals("Q")) {
			quit();
		}		
	}
	

	public static boolean checkAccount (String userName, String userPassword){
		String dataUserName = null;
		String dataPassword = null;
		
		
		// 1. create a database connection
	    Connection conn = null;
	    try {
			conn = DriverManager.getConnection(Database.url, Database.username, Database.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	    
	    // 2. prepare and execute statement
	    String s = "select userName, password from UserDetails where userName = '"+ userName + "';";
	    PreparedStatement prep = null;
	    ResultSet rs = null;
	    try {
			prep = conn.prepareStatement(s);
			rs = prep.executeQuery();
			
			while(rs.next()) {
				dataUserName = rs.getString("userName");
				dataPassword = rs.getString("password");
				/*
				System.out.println("Database.username User input:" + userName);
				System.out.println("Password user input:" + userPassword);
				System.out.println("User in Database:" + dataUserName);
				System.out.println("Password in Database:" + dataPassword);
				*/
			}
	
		
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 3. close all resources
	    try {
			if (rs != null) 
				rs.close();
			if (prep != null)
				prep.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	   if(dataUserName.equals(userName) && dataPassword.equals(userPassword)) {
		   return true;
	   } else {
		  return false;
	   }
		
	}
	
	public static void login() {
		Views.loginWindow();
		//do loop till matches or user quits
		System.out.print("Please enter your username: ");
		String userName = input.next();
		
		System.out.print("Please enter your password: ");
		String userPassword = input.next();
		
		boolean check = checkAccount(userName, userPassword);
			if(check == true) {
				CurrentUser = userName;	
				getUserID();
				System.out.println("U MADE IT!!!");
				homeInterface();
				
			} else {
				while (check == false) {
					String userChoice = null;
					System.out.println("Wrong password and/or username");
					System.out.println("Try again press [T]; to return to main [M]; to Quite press [Q]");
					userChoice = input.next();
						
					while((userChoice.equals("T") || userChoice.equals("M") ||  userChoice.equals("Q")) == false){
						System.out.println("Enter a valid character please");
						userChoice= input.next();
					}
						
					if (userChoice.equals("T")) {
						System.out.print("Please enter your username: ");
						userName = input.next();
						System.out.print("Please enter your password: ");
						userPassword = input.next();
						check = checkAccount(userName, userPassword);
					}
					
					if(userChoice.equals("M")) {
						mainInterface();
					}
						
					if(userChoice.equals("Q")) {
						quit();
					}
				}//end of while loop
			 System.out.println("U MAdE IT TO SETTINGS!!");
			 homeInterface();
			} // end of else
	}
	
	public static void quit() {
		input.close();
		System.out.println("Good Bye!");
		System.exit(0);
		//Erases User Data
	}

	public static void getUserID(){
		// 1. create a database connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(Database.url, Database.username, Database.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    
	    // 2. prepare and execute statement
	    String s = "select userID from UserDetails where userName = '"+ CurrentUser + "';";
	    PreparedStatement prep = null;
	    ResultSet rs = null;
	    
	    try {
			prep = conn.prepareStatement(s);
			rs = prep.executeQuery();
			
			while(rs.next()) {
				userID = rs.getInt("userID");
			}
	
		
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 3. close all resources
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
	
	public static void logout() {
		CurrentUser = null;
		userID = null;
		mainInterface();
	}
}