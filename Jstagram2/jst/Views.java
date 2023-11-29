package jst;
import java.util.ArrayList;


public class Views {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_Green = "\u001B[32m";

    public static final String ANSI_Yellow = "\u001B[33m";

    public static final String ANSI_Blue = "\u001B[34m";

    public static final String ANSI_Cyan = "\u001B[36m";

    public static void mainWindow() {
        System.out.println(ANSI_Green + " ========================================");
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
        System.out.printf("|%-20s", "Login(L) or Quit(Q)","|%-20\n");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println(" ========================================" + ANSI_RESET);
    }

    public static void loginWindow() {
        System.out.println(ANSI_Blue + " ========================================");
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
        System.out.println(" ========================================" + ANSI_RESET);
    }
   
    public static void accountWindow() {
        System.out.println(ANSI_Yellow + " ========================================");
 		System.out.printf("|%-40s|\n"," _   _                         ");
 		System.out.printf("|%-40s|\n","| | | | ___  _ __ ___   ___    ");    
 		System.out.printf("|%-40s|\n","| |_| |/ _ \\| '_ ` _ \\ / _ \\");         
 		System.out.printf("|%-40s|\n","|  _  | (_) | | | | | |  __/   ");    
 		System.out.printf("|%-40s|\n","|_| |_|\\___/|_| |_| |_|\\___|  ");
        System.out.printf("|%-40s|\n","Post(+) Visibility(V) Logout(L) Quit(Q)");
        System.out.println(" ========================================" + ANSI_RESET);
      
    }
    //deal with later
    public static void vizWindow() {
        System.out.println(ANSI_Cyan +  "+========================================+" + ANSI_RESET);
        System.out.println(ANSI_Yellow + "|-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+|");
        System.out.println("|/////////|V|i|s|i|b|i|l|i|t|y|//////////|");
        System.out.println("|-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+|" + ANSI_RESET);
  
        System.out.println(ANSI_Cyan + "|                                        |");
        
        System.out.printf("|%36s %5s","----- Who Can See My Posts -----","|\n");
        for(int i =0; i < Main.userViz.size(); i++) {
			String s = Main.userViz.get(i);
			System.out.printf("|%5s %-15s %20s","-|",s, "|\n");
		}        
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.printf("|%38s %3s","----- Who Can NOT See My Posts -----","|\n");
        for(int i =0; i < Main.userNoViz.size(); i++) {
			String s = Main.userNoViz.get(i);
			System.out.printf("|%5s %-15s %20s","+|",s, "|\n");
		}
        System.out.println("|                                        |");
        System.out.printf("|%30s %11s","----- Options -----","|\n");
        System.out.printf("|%5s %10s %23s","|", "Add User[+] ","|\n");
        System.out.printf("|%5s %10s %20s","|", "Remove User[-] ","|\n");
        System.out.printf("|%5s %-10s %25s","|", "Back[B] ","|\n");
        System.out.println("|                                        |");
        System.out.println("+========================================+" + ANSI_RESET);
    }
    /*
     *   if(userChoice.equals("+")) {
        	System.out.println(ANSI_Cyan + " ========================================");
        	System.out.printf("|%-40s|\n", "          Type Your Text Below          ");
        	System.out.println(" ========================================" + ANSI_RESET);
        }
     */

    
}
