package com.qq.common;

import java.io.Serializable;

/**
 * This is a class to abstract a user
 * @author Zhang
 *
 */
public class User implements Serializable{
	
	private String userID;
	private String password;
	
	public User(String name, String password) {
		this.setUserID(name);
		this.setPassword(password);
	}
	
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
