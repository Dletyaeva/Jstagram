package jstagram;

import java.util.List;

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
        System.out.println("|          Login(L) or Quit(Q)   		 |");
        System.out.println("|                                        |");
        System.out.println("|                                        |");
        System.out.println(" ========================================" + ANSI_RESET);
    }

  

    public static void accountLoginWindow() {
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


    public static void postViewWindow(List<Post> posts) {
        System.out.println(ANSI_Cyan + " ========================================");
        for (Post p : posts) {
            System.out.print(p.getFormattedContent());
            System.out.printf("|%40s|\n", p.tag());
        }
        System.out.println("|  New Post(+) or Logout(L) or Quit(Q)   |");
        System.out.println(" ========================================" + ANSI_RESET);
    }
    

    public static void displayTextPostOptions() {
    	System.out.println(ANSI_Cyan + " ========================================");
    	System.out.printf("|%-40s|\n", "          Type Your Text Below          ");
    	System.out.println(" ========================================" + ANSI_RESET);
    }
 
    
}
