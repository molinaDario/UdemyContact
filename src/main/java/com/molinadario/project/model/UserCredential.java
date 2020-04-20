package com.molinadario.project.model;

public class UserCredential {

	private String user;
	private String pass;

	public UserCredential() {
	}

	public UserCredential(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "UserCredential [user=" + user + ", pass=" + pass + "]";
	}

}
