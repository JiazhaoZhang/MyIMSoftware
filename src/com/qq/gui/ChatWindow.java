package com.qq.gui;

import javax.swing.*;

import com.qq.common.*;
import com.qq.model.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ChatWindow extends JFrame implements ActionListener/* ,Runnable */{
	
	JTextArea contentArea,messageArea;
	JButton sendButton,closeButton;
	
	JPanel buttonPanel,southPanel;
	JScrollPane contentScrollPane,messageScrollPane;
	
	private String sender;
	private String getter;
	private UserManager um;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ChatWindow cw = new ChatWindow("Lisa");
	}
	
	public ChatWindow(String sender, String getter, UserManager um) {
		
		contentArea = new JTextArea();
		contentArea.setEditable(false);
		messageArea = new JTextArea();
		sendButton = new JButton("SEND");
		sendButton.addActionListener(this);
		sendButton.setActionCommand("send");
		closeButton = new JButton("CLOSE");
		closeButton.addActionListener(this);
		closeButton.setActionCommand("close");
		contentScrollPane = new JScrollPane(contentArea);
		messageScrollPane = new JScrollPane(messageArea);
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(sendButton);
		buttonPanel.add(closeButton);
		southPanel = new JPanel(new GridLayout(2,1));
		southPanel.add(messageScrollPane);
		southPanel.add(buttonPanel);
		
		this.add(contentScrollPane,"Center");
		this.add(southPanel,"South");
		
		this.setSize(450,350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		this.setSender(sender);
		this.setGetter(getter);
		this.setUm(um);
		this.setTitle(sender+" chats with "+getter);
		
	}
	
	public void showMessageContent(String message) {
		
		this.contentArea.append(message+"\r\n");
		
	}
	
	/**
	 * @return the um
	 */
	public UserManager getUm() {
		return um;
	}

	/**
	 * @param um the um to set
	 */
	public void setUm(UserManager um) {
		this.um = um;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("send")) {
			
			String info = messageArea.getText();
			contentArea.append("You say : "+info+"\n");
			messageArea.setText("");
			
			Message mes = new Message();
			mes.setSender(this.getSender().trim());
			mes.setGetter(this.getGetter().trim());
			mes.setContentOfMessage(info.trim());
			mes.setType(2);
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(this.getUm().getMc().getSk().getOutputStream());
				oos.writeObject(mes);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(e.getActionCommand().equals("close")) {
			
			this.dispose();
		}
	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		while(true) {
//			try {
//				ObjectInputStream ois = new ObjectInputStream(this.getUm().getMc().getSk().getInputStream());
//				Message me = (Message) ois.readObject();
//				String info = me.getSender() + "says to "+me.getGetter()+" "+me.getContentOfMessage()+"\r\n";
//				contentArea.append(info);
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	
}
