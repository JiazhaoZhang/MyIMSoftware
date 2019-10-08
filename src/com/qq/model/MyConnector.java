package com.qq.model;

import java.io.*;
import java.net.*;

import com.qq.common.*;

public class MyConnector {
	
	private Socket sk ;
	
	public MyConnector(String url, int port) {
		try {
			sk = new Socket(url,port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @return the sk
	 */
	public Socket getSk() {
		return sk;
	}



	/**
	 * @param sk the sk to set
	 */
	public void setSk(Socket sk) {
		this.sk = sk;
	}



	public Message sendUserInfo(Object obj) {
		
		Message me = null;
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(sk.getOutputStream());
			oos.writeObject(obj);
			ObjectInputStream ois = new ObjectInputStream(sk.getInputStream());
			me = (Message) ois.readObject();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return me;
	}
}
