package jst;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Post {
	public static List<String> Input40 = new ArrayList<>();
	
	public static void setTextInput(String input) {
	/*really long string over 40 characters 
	 * split it into an array, each string at some index to be exactly 40 characters
	 */	
		Integer mult = input.length() / 40;
		Integer length = 40 * mult;
		
		for(int i=0; i < mult; i++) {
			String newString = input.substring(i*40, length-((mult-(i+1))*40));
			Input40.add(newString);
		}
		
		//adds any remaining characters into Input40
		Input40.add(input.substring(length, input.length()));		
	}
	
	public static void printSetTextInput() {
		for(int i=0; i < Input40.size(); i++) {
			String string = Input40.get(i).toString();
			System.out.printf("|%-40s|\n", string);
		}
	}

	public static void addTextPost() {
		Views.textPostPrompt();
		// Create a database connection
	    Connection conn = null;
	    try {
			conn = DriverManager.getConnection(Database.url, Database.username, Database.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    
		//consumes an extra newLine, otherwise won't user take input
	    Main.input.nextLine();
	    
	    // Get user input
	    System.out.print("Enter text: ");
	    String userText = Main.input.nextLine();

		
		LocalDateTime postTime = LocalDateTime.now();
		String date = postTime.format(DateTimeFormatter.ofPattern("hh:mm:ssa MMM dd, YYYY"));

	    // Prepare statement
	    String s = """
	    			insert into PostDetails (postText, postTime, userID)
	    		             	values  (?,         ?,           ?);
	    			""";
	    
	    PreparedStatement prep = null;
	    try {
			prep = conn.prepareStatement(s);
			
			prep.setString(1, userText); 
			prep.setString(2, date); 
			prep.setInt(3, Main.userID); 
			// index starts at 1 not 0
			
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 3. close all resources
	    // do not combine with step 2
	    // otherwise, resources are not closed when something wrong at step 2
	    try {
			if (prep != null)
				prep.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void deletePost(){
		
		// Create a database connection
	    Connection conn = null;
	    try {
			conn = DriverManager.getConnection(Database.url, Database.username, Database.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    
		// Get user input

	    // Prepare statement
	    String s = """
	    			DELETE from PostDetails WHERE postID = 7;
	    			""";
	    PreparedStatement prep = null;
	    try {
			prep = conn.prepareStatement(s);

			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 3. close all resources
	    // do not combine with step 2
	    // otherwise, resources are not closed when something wrong at step 2
	    try {
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
			conn = DriverManager.getConnection(Database.url, Database.username, Database.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	    
	    // 2. prepare and execute statement
	    String s = "select postText, userName, postTime from PostDetails natural inner join UserDetails natural inner join VIZ \r\n"
	    		+ "where "+ Main.CurrentUser + " ='Y'  and userName in (select userName from UserDetails)  order by postID desc;";
	    PreparedStatement prep = null;
	    ResultSet rs = null;
	    try {
			prep = conn.prepareStatement(s);
			
			rs = prep.executeQuery();
			
			while(rs.next()) {
				String userName = rs.getString("userName");
				String postTime = rs.getString("postTime");
				String postText = rs.getString("postText");
				
				if (postText.length() >= 40) {
					Post.Input40.clear();
					Post.setTextInput(postText);
					Post.printSetTextInput();
				} else {
					System.out.printf(Views.ANSI_Cyan + "|");				
					System.out.printf(Views.ANSI_Red + "   %-37s",postText);
					System.out.printf(Views.ANSI_Cyan +"|\n");
				}
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
}