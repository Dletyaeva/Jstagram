package jstagram;

import java.time.LocalDateTime;
import java.util.*;



public class TextPost extends Post implements PostContentFormatter {
	
	private String userInput;
	private List<String> Input40 = new ArrayList<>();
	
	TextPost(){
		
	}
	
	public TextPost(String input, Account currentUser) {
		this.userInput = input;
		this.postTime = LocalDateTime.now();
		this.postAccount = currentUser;	
		setTextInput(userInput);
	}
	
	public void setTextInput(String input) {
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
	
	public void printSetTextInput() {
		for(int i=0; i < Input40.size(); i++) {
			String string = Input40.get(i).toString();
			System.out.printf("|%-40s|\n", string);
		}
	}

	@Override
	public String getFormattedContent() {
		if(userInput.length() >= 40) {
			printSetTextInput();
		} else {
		System.out.printf("|%-40s|\n", userInput);
		}
		String stringing = "|                                        |" + "\n";	
		return stringing;
	}

}
