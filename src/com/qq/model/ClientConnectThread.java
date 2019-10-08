package com.qq.model;
/**
 * This is a thread which the client uses to
 * receive message from the server 
 */
import java.io.*;
import java.net.*;
import com.qq.common.*;
import com.qq.gui.ChatWindow;

public class ClientConnectThread extends Thread{
	
	private Socket sk;
	
	public ClientConnectThread(Socket socket) {
		this.setSk(socket);
	}
	
	@Override
	public void run() {
		
		while(true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(this.getSk().getInputStream());
				Message readMessage = (Message) ois.readObject();
				System.out.println("Sender is : "+readMessage.getSender());
				System.out.println("Getter is : "+readMessage.getGetter());
				System.out.println("Content is : "+readMessage.getContentOfMessage());
				ChatWindow cw = UserManager.getCollectionOfChatWindows().get(readMessage.getGetter()+"+"+readMessage.getSender());
				cw.showMessageContent(readMessage.getSender()+" says to "+readMessage.getGetter()+" : "+readMessage.getContentOfMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	
}
