package jst;


public class Views {
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static final String ANSI_Red = "\u001B[31m";

    public static final String ANSI_Green = "\u001B[32m";

    public static final String ANSI_Yellow = "\u001B[33m";
    
    public static final String ANSI_Blue = "\u001B[34m";
    
    public static final String ANSI_Purple = "\u001B[35m";

    public static final String ANSI_Cyan = "\u001B[36m";
    
    
    
    
    public static void mainWindow() {
        System.out.println(ANSI_Green + "+========================================+");
        System.out.println("|         Welcome to Jstgram!            |");
        System.out.println("|                                        |");
        System.out.println("|              *************             |");
        System.out.println("|                    *                   |");
        System.out.println("|                    *                   |");
        System.out.println("|                    *                   |");
        System.out.println("|                    *                   |");
        System.out.println("|              *     *                   |");
        System.out.println("|              *******                   |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.printf("|%30s %11s", "Login[L] or Quit[Q]","|\n");
        System.out.println("|                                        |");
        System.out.printf("|%-6s| %-20s |%-6s|\n","","Users in the Database: "+Main.usersOn,"");
        System.out.println("|                                        |");
        System.out.println("+========================================+" + ANSI_RESET);
    }

    public static void loginWindow() {
        System.out.println(ANSI_Cyan + "+========================================+");
        System.out.println("|                 Login                  |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|         * User Name   _______          |");
        System.out.println("|         * Password    _______          |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println("+========================================+" + ANSI_RESET);
    }
   
    public static void accountWindow() {
        System.out.println(ANSI_Yellow + "+========================================+");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
 		System.out.printf("|%-40s|\n","       _   _");
 		System.out.printf("|%-40s|\n","      | | | | ___  _ __ ___   ___    ");    
 		System.out.printf("|%-40s|\n","      | |_| |/ _ \\| '_ ` _ \\ / _ \\");         
 		System.out.printf("|%-40s|\n","      |  _  | (_) | | | | | |  __/   ");    
 		System.out.printf("|%-40s|\n","      |_| |_|\\___/|_| |_| |_|\\___|  ");
 		System.out.println("|                                        |");
 		System.out.println("|                                        |");
 		System.out.println("|                                        |");
 		System.out.printf("|%10s %-15s %-10s %4s","|","  Post[+]","|","|\n");
 		System.out.printf("|%10s %-15s %-10s %4s","|","  Visibility[V]","|","|\n");
 		System.out.printf("|%10s %-15s %-10s %4s","|","  Logout[L]","|","|\n");
 		System.out.printf("|%10s %-15s %-10s %4s","|","  Quit[Q]","|","|\n");		
 		System.out.println("|                                        |");
 		System.out.printf("|%-8s| %-20s |%-8s|\n","","Current User: "+Main.CurrentUser,"");
 		System.out.println("|                                        |");
        System.out.println("+========================================+" + ANSI_RESET);
      
    }
    //deal with later
    public static void vizWindow() {
        System.out.println(ANSI_Cyan +  "+========================================+" + ANSI_RESET);
        System.out.println(ANSI_Yellow + "|-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+|");
        System.out.println("|-+-+-+-+-|V|i|s|i|b|i|l|i|t|y|-+-+-+-+-+|");
        System.out.println("|-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+|" + ANSI_RESET);
  
        System.out.println(ANSI_Cyan + "|                                        |");
        
        System.out.printf("|%36s %5s","----- Who Can See My Posts -----","|\n");
        for(int i =0; i < Viz.userViz.size(); i++) {
			String s = Viz.userViz.get(i);
			System.out.printf(ANSI_Cyan + "|");
			System.out.printf(ANSI_Green + "%5s %-15s","-|",s);
			System.out.printf(ANSI_Cyan + "%21s","|\n");
		}        
        System.out.println(ANSI_Cyan + "|                                        |");
        System.out.println("|                                        |");
        System.out.printf("|%38s %3s","----- Who Can NOT See My Posts -----","|\n");
        for(int i =0; i < Viz.userNoViz.size(); i++) {
			String s = Viz.userNoViz.get(i);
			System.out.printf(ANSI_Cyan + "|");
			System.out.printf(ANSI_Green + "%5s %-15s","+|",s);
			System.out.printf(ANSI_Cyan + "%21s","|\n");
		}
        System.out.println(ANSI_Cyan + "|                                        |");
        System.out.printf("|%30s %11s","----- Options -----","|\n");
        System.out.printf("|%5s %10s %23s","|", "Add User[+] ","|\n");
        System.out.printf("|%5s %10s %20s","|", "Remove User[-] ","|\n");
        System.out.printf("|%5s %-10s %25s","|", "Back[B] ","|\n");
        System.out.println("|                                        |");
        System.out.print("|");
        System.out.printf(ANSI_Purple + "%-8s| %-20s |%-8s","","Current User: "+ Main.CurrentUser,"");
        System.out.print(ANSI_Cyan + "|\n");
        System.out.println(ANSI_Cyan + "|                                        |");
        System.out.println("+========================================+" + ANSI_RESET);
    }
    public static void postWindow() {
        System.out.println(ANSI_Cyan +  "+========================================+" + ANSI_RESET);
        System.out.println(ANSI_Cyan + "|" + ANSI_Yellow + "-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+" + ANSI_Cyan + "|");
        System.out.println(ANSI_Cyan + "|" + ANSI_Yellow + "-+-+-+-+-+-+-+|P|o|s|t|s|+-+-+-+-+-+-+-+" + ANSI_Cyan + "|");
        System.out.println(ANSI_Cyan + "|" + ANSI_Yellow + "-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+" + ANSI_Cyan + "|" );
        System.out.println("|                                        |");
        Post.viewPost();
        System.out.println(ANSI_Cyan + "|                                        |");
        System.out.printf("|%5s %-10s %23s","|", "New Post [+]","|\n");
        System.out.printf("|%5s %-10s %25s","|", "Back[B] ","|\n");
        System.out.println("|                                        |");
        System.out.print("|");
        System.out.printf(ANSI_Purple + "%-8s| %-20s |%-8s","","Current User: "+ Main.CurrentUser,"");
        System.out.print(ANSI_Cyan + "|\n");
        System.out.println("|                                        |");
        System.out.println("+========================================+" + ANSI_RESET);
    }
    public static void textPostPrompt() {
        	System.out.println(ANSI_Cyan + " ========================================");
        	System.out.printf("|%-40s|\n", "          Type Your Text Below          ");
        	System.out.println(" ========================================" + ANSI_RESET);
        }

    
}
