package jstagram;


public class Account {
	private String userName;
	private String password;
	private String phoneNumber;

	public Account() {

	}

	public Account(String userName, String password, String phoneNumber) {
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;

	}

	// getter methods
	public String getUsername() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
}
