package jst;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Viz {
	public static ArrayList<String> userViz = new ArrayList<String>();
	public static ArrayList<String> userNoViz = new ArrayList<String>();
	public static void generateVizNoSee() {
		// 1. create a database connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(Database.url, Database.username, Database.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    // 2. prepare and execute statement
	    String s = "select userName from VIZ natural inner join UserDetails \r\n"
	    		+ "	where ("+ Main.CurrentUser +"= 'N') and userName in (select userName from UserDetails);";
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
			conn = DriverManager.getConnection(Database.url, Database.username, Database.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    // 2. prepare and execute statement
	    String s = "select userName from VIZ natural inner join UserDetails \r\n"
	    		+ "	where ("+ Main.CurrentUser +"= 'Y') and userName in (select userName from UserDetails);";
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
	

	public static void addToViz() {
		// Create a database connection
	    Connection conn = null;
	    try {
			conn = DriverManager.getConnection(Database.url, Database.username, Database.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    
		// Get user input
		System.out.print("Enter Database.username to add: ");
		String addUser = Main.input.nextLine();

	    // Prepare statement
	    String s = "update VIZ set " + Main.CurrentUser + " = 'Y' where userID =(select userID from UserDetails where userName = "+ addUser + ");";
	    
	    PreparedStatement prep = null;
	    
	    try {
			prep = conn.prepareStatement(s);

			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    try {
			if (prep != null)
				prep.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	// deletes a user from current user's visibility list
	public static void deleteFromViz() {
		// Create a database connection
	    Connection conn = null;
	    try {
			conn = DriverManager.getConnection(Database.url, Database.username, Database.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    
		// Get user input
		System.out.print("Enter Database.username to remove: ");
		String addUser = Main.input.nextLine();

	    // Prepare statement
	    String s = "update VIZ set " + Main.CurrentUser + " = 'N' where userID =(select userID from UserDetails where userName = "+ addUser + ");";
	    
	    PreparedStatement prep = null;
	    
	    try {
			prep = conn.prepareStatement(s);

			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    try {
			if (prep != null)
				prep.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}