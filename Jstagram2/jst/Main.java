package jst;
import java.sql.*;
import java.util.*;

import unl.soc.Account;


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
	
	public static String CurrentUser = "Alice";
	
	private final static Scanner input = new Scanner(System.in);
	
	public static void main (String[] args) {
		//login();
		boolean account = checkAccount("Alice", "Alice123");
		//viewPost();
		System.out.println("account is found:" + account);
	}
	
	
	public static void  viewPost() {
		// 1. create a database connection
	    Connection conn = null;
	    try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    
	    // 2. prepare and execute statement
	    String s = "select postText, userName, postTime from PostDetails natural inner join UserDetails natural inner join VIZ \r\n"
	    		+ "where "+ CurrentUser + " ='Y'  and userName in (select userName from UserDetails)  order by postID desc;";
	    PreparedStatement prep = null;
	    ResultSet rs = null;
	    try {
			prep = conn.prepareStatement(s);
			
			rs = prep.executeQuery();
			
			while(rs.next()) {
				String userName = rs.getString("userName");
				String postTime = rs.getString("postTime");
				String postText = rs.getString("postText");
				
				System.out.println(userName+"\t"+postText+"\t"+postTime);
			}
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

	
	
	public static boolean checkAccount (String userName, String Password){
		String dataUserName = null;
		String dataPassword = null;
		boolean check = false;
		
		// 1. create a database connection
	    Connection conn = null;
	    try {
			conn = DriverManager.getConnection(url, username, password);
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
				
				if (dataUserName == userName) {
					if (dataPassword == Password) {
						check = true;
					}
				} else {
					check = false;
				}
				
				System.out.println("username User input:" + userName);
				System.out.println("Password user input:" + Password);
				System.out.println("User in Database:" + dataUserName);
				System.out.println("Password in Database:" + dataPassword);
				System.out.println(check);
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
	    
		if ((dataUserName == userName) && (dataPassword == Password)) {
			return true;
		} else {
			return false;
		}
		
	}
	public  void login() {
		//Make loop till matches or user quits
		System.out.print("Please enter your username: ");
		String userName = input.next();
		
		System.out.print("Please enter your password: ");
		String password = input.next();
		
		boolean check = checkAccount(userName);
		
		
		if(accounts.containsKey(userName)) {
			Account stored = accounts.get(userName);
			String passStored = stored.getPassword();
			
			boolean check = passStored.equals(password);
			
				if(check == true) {
				 this.currentAccount = accounts.get(userName);
				 activity();
					
				} else {
					while (check == false) {
						String userChoice = null;
						System.out.println("Wrong Password");
						System.out.println("Try again press [T]; to return to main [M]; to Quite press [Q]");
						userChoice = input.next();
						
						while((userChoice.equals("T") || userChoice.equals("M") ||  userChoice.equals("Q")) == false){
							System.out.println("Enter a valid character please");
							userChoice= input.next();
						}
						
						if (userChoice.equals("T")) {
							System.out.print("Please enter your password: ");
							password = input.next();
							check = passStored.equals(password);
						}
						if(userChoice.equals("M")) {	
						}
						
						if(userChoice.equals("Q")) {
							quit();
						}
		
					}
				}
	
			
		} else {
			System.out.println("User not found, returning to main page");
		}
	}
	
	public void quit() {
		input.close();
		System.out.println("Good Bye!");
		System.exit(0);
		//Erases User Data
	}
	
}