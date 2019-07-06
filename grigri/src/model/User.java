package model;

import java.io.Serializable;

public class User implements Serializable{

	PassTest e = new PassTest("SHA-256");
	byte[] bytes = e.toHashValue("dammy");
	String result = e.toEncryptedString(bytes);

	private String userName,
				   userPass;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public User(){
		userName = "dammy";
		userPass = result;
	}

}
