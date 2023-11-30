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
	public static ArrayList<String> userViz = new ArrayList<String>();
	public static ArrayList<String> userNoViz = new ArrayList<String>();
	public static String CurrentUser = "Alice";
	
	private final static Scanner input = new Scanner(System.in);
	
	public static void main (String[] args) {
		//Views.mainWindow();
		//Views.loginWindow();
		//Views.accountWindow();
		Views.postWindow();
		generateVizSee();
		generateVizNoSee();
		//login();
		//viewPost();
		//Views.vizWindow();
	}
	
	//behind the scenes stuff
	public static void generateVizNoSee() {
		// 1. create a database connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    // 2. prepare and execute statement
	    String s = "select userName from VIZ natural inner join UserDetails \r\n"
	    		+ "	where ("+ CurrentUser +"= 'N') and userName in (select userName from UserDetails);";
	    PreparedStatement prep = null;
	    ResultSet rs = null;
	    try {
			prep = conn.prepareStatement(s);
			
			rs = prep.executeQuery();
			
			while(rs.next()) {
				String userName = rs.getString("userName");
				userNoViz.add(userName);
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
	
	public static void generateVizSee() {
		// 1. create a database connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    // 2. prepare and execute statement
	    String s = "select userName from VIZ natural inner join UserDetails \r\n"
	    		+ "	where ("+ CurrentUser +"= 'Y') and userName in (select userName from UserDetails);";
	    PreparedStatement prep = null;
	    ResultSet rs = null;
	    try {
			prep = conn.prepareStatement(s);
			
			rs = prep.executeQuery();
			while(rs.next()) {
				String userName = rs.getString("userName");
				userViz.add(userName);
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
				System.out.printf(Views.ANSI_Cyan + "|");				
				System.out.printf(Views.ANSI_Red + "   %-37s",postText);
				System.out.printf(Views.ANSI_Cyan +"|\n");
				System.out.printf(Views.ANSI_Cyan + "|");
				System.out.printf(Views.ANSI_Yellow + "%37s   ", userName + ", " + postTime);
				System.out.printf(Views.ANSI_Cyan +"|\n");
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

	public static boolean checkAccount (String userName, String userPassword){
		String dataUserName = null;
		String dataPassword = null;
		
		
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
				/*
				System.out.println("username User input:" + userName);
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
		//do loop till matches or user quits
		System.out.print("Please enter your username: ");
		String userName = input.next();
		
		System.out.print("Please enter your password: ");
		String userPassword = input.next();
		
		boolean check = checkAccount(userName, userPassword);
			if(check == true) {
				CurrentUser = userName;	
				System.out.println("U MADE IT!!!");
				//go to user Setting page
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
					}
						
					if(userChoice.equals("Q")) {
						quit();
					}
				}//end of while loop
			 System.out.println("U MAdE IT TO SETTINGS!!");
			} // end of else
	}
	
	public static void quit() {
		input.close();
		System.out.println("Good Bye!");
		System.exit(0);
		//Erases User Data
	}
	
	
	//stuff to deal with later
	/*
	 * 	public void printSetTextInput() {
		for(int i=0; i < Input40.size(); i++) {
			String string = Input40.get(i).toString();
			System.out.printf("|%-40s|\n", string);
		}
	}

	@Override
	public String getFormattedContent() {
		if(userInput.length() >= 40) {
			
		} else {
		System.out.printf("|%-40s|\n", userInput);
		}
		String stringing = "|                                        |" + "\n";	
		return stringing;
	}
	 */
	
}