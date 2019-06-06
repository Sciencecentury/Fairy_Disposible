package model;

import java.io.Serializable;

public class User implements Serializable{

	private String userName;
	private PassTest userPass;

	public User(){
		this.userName = null;
		this.userPass = null;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public PassTest getUserPass() {
		return userPass;
	}
	public void setUserPass(PassTest userPass) {
		this.userPass = userPass;
	}
}
