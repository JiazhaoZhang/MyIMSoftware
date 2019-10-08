package com.qq.model;

import java.util.*;
import com.qq.common.*;
import com.qq.gui.*;

public class UserManager {
	
	MyConnector mc;
	ClientConnectThread cct;
	private static HashMap<String,ChatWindow> collectionOfChatWindows = new HashMap<String, ChatWindow>();
	
	public UserManager( MyConnector mc , ClientConnectThread cct) {
		
		this.mc =mc;
		this.cct = cct;
		
	}
	
	public boolean validateUser(User user) {

		Message me = mc.sendUserInfo(user);
		return me.getType()==1;
		
	}
	public void startClientConnectThread() {
		cct.start();
	}
	/**
	 * @return the mc
	 */
	public MyConnector getMc() {
		return mc;
	}

	/**
	 * @param mc the mc to set
	 */
	public void setMc(MyConnector mc) {
		this.mc = mc;
	}

	/**
	 * @return the cct
	 */
	public ClientConnectThread getCct() {
		return cct;
	}

	/**
	 * @param cct the cct to set
	 */
	public void setCct(ClientConnectThread cct) {
		this.cct = cct;
	}

	/**
	 * @return the collectionOfChatWindows
	 */
	public static HashMap<String, ChatWindow> getCollectionOfChatWindows() {
		return collectionOfChatWindows;
	}

	/**
	 * @param collectionOfChatWindows the collectionOfChatWindows to set
	 */
	public static void setCollectionOfChatWindows(HashMap<String, ChatWindow> collectionOfChatWindows) {
		UserManager.collectionOfChatWindows = collectionOfChatWindows;
	}
	
	
}
