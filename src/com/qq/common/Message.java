package com.qq.common;

import java.io.Serializable;

public class Message implements Serializable{
	
	private int type;
	private String sender;
	private String getter;
	private String contentOfMessage;

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the getter
	 */
	public String getGetter() {
		return getter;
	}

	/**
	 * @param getter the getter to set
	 */
	public void setGetter(String getter) {
		this.getter = getter;
	}

	/**
	 * @return the contentOfMessage
	 */
	public String getContentOfMessage() {
		return contentOfMessage;
	}

	/**
	 * @param contentOfMessage the contentOfMessage to set
	 */
	public void setContentOfMessage(String contentOfMessage) {
		this.contentOfMessage = contentOfMessage;
	}
	
	
	
}
