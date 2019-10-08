package com.qq.gui;
import javax.swing.*;

import com.qq.model.*;

import java.awt.*;
import java.awt.event.*;


public class MainWindow extends JFrame implements ActionListener,MouseListener{
	
	JButton friendsList_Friend,strangersList_Friend,blackList_Friend;
	JButton friendsList_S,strangersList_S,blackList_S;
	JButton friendsList_B,strangersList_B,blackList_B;
	
	JPanel friendPanel,strangerPanel, blackListPanel;
	JScrollPane scrollPaneFriend, scrollPaneStranger, scrollPaneBlackList;
	JPanel userListPanel, strangerListPanel, enemyListPanel;
	JLabel[] friendsLabels , strangersLabels, blacklistsLabel;
	
	CardLayout cl;
	
	private String sender;
	private UserManager um;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MainWindow mw = new MainWindow();
	}
	
	public MainWindow(String sender,UserManager um)
	{
		friendsList_Friend = new JButton("My Friends");
		strangersList_Friend = new JButton("Strangers");
		blackList_Friend = new JButton("My Black List");
		
		friendsList_S = new JButton("My Friends");
		strangersList_S = new JButton("Strangers");
		blackList_S = new JButton("My Black List");
		
		friendsList_B = new JButton("My Friends");
		strangersList_B = new JButton("Strangers");
		blackList_B = new JButton("My Black List");
		
		friendsLabels = new JLabel[30];
		strangersLabels = new JLabel[30];
		blacklistsLabel = new JLabel[30];
		
		//create a panel for friends
		for(int i=0;i<friendsLabels.length;i++) {
			ImageIcon icon = new ImageIcon("image/qqicon.jpg");
			icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			friendsLabels[i]=new JLabel("f"+i, icon ,JLabel.LEFT);
			friendsLabels[i].addMouseListener(this);
			friendsLabels[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		}
		
		userListPanel = this.createListPanel(friendsLabels);
		scrollPaneFriend = new JScrollPane(userListPanel);
		
		friendPanel = this.makePanel(friendsList_Friend, strangersList_Friend, blackList_Friend, 1);
		friendPanel.add(scrollPaneFriend);
		//this.add(friendPanel);
		
		//create a panel for strangers
		for(int i=0;i<strangersLabels.length;i++) {
			ImageIcon icon_stranger = new ImageIcon("image/stranger.jpg");
			icon_stranger.setImage(icon_stranger.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			strangersLabels[i]=new JLabel("Stranger NO."+i, icon_stranger ,JLabel.LEFT);
			strangersLabels[i].addMouseListener(this);
			strangersLabels[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		strangerPanel = this.makePanel(friendsList_S, strangersList_S, blackList_S, 2);
		strangerListPanel = this.createListPanel(strangersLabels);
		scrollPaneStranger = new JScrollPane(strangerListPanel);
		strangerPanel.add(scrollPaneStranger);
		//this.add(strangerPanel);
		
		//create a panel for blacklist
		for(int i=0;i<blacklistsLabel.length;i++) {
			ImageIcon icon_enemy = new ImageIcon("image/enemy.jpg");
			icon_enemy.setImage(icon_enemy.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			blacklistsLabel[i]=new JLabel("Enemy NO."+i, icon_enemy ,JLabel.LEFT);
			blacklistsLabel[i].addMouseListener(this);
			blacklistsLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		enemyListPanel = this.createListPanel(blacklistsLabel);
		blackListPanel = this.makePanel(friendsList_B, strangersList_B, blackList_B, 3);
		scrollPaneBlackList = new JScrollPane(enemyListPanel);
		blackListPanel.add(scrollPaneBlackList);
		//this.add(blackListPanel);
		
		cl = new CardLayout();
		this.setLayout(cl);
		this.add(friendPanel,"friend");
		this.add(strangerPanel,"stranger");
		this.add(blackListPanel,"blacklist");
		
		//set buttons' events
		strangersList_Friend.addActionListener(this);
		strangersList_Friend.setActionCommand("stranger");
		blackList_Friend.addActionListener(this);
		blackList_Friend.setActionCommand("blacklist");
		friendsList_S.addActionListener(this);
		friendsList_S.setActionCommand("friend");
		blackList_S.addActionListener(this);
		blackList_S.setActionCommand("blacklist");
		friendsList_B.addActionListener(this);
		friendsList_B.setActionCommand("friend");
		strangersList_B.addActionListener(this);
		strangersList_B.setActionCommand("stranger");
		
		this.setSender(sender);
		this.setUm(um);
		
		this.setTitle(sender);
		this.setVisible(true);
		this.setSize(300, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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

	public JPanel makePanel(JButton btn1,JButton btn2,JButton btn3, int type) {
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		
		switch(type) {
			case 1:
				northPanel.setLayout(new GridLayout(1,1));
				northPanel.add(btn1);
				southPanel.setLayout(new GridLayout(2,1));
				southPanel.add(btn2);
				southPanel.add(btn3);
				panel.add(northPanel,BorderLayout.NORTH);
				panel.add(southPanel,BorderLayout.SOUTH);
				break;
			case 2:
				northPanel.setLayout(new GridLayout(2,1));
				northPanel.add(btn1);
				northPanel.add(btn2);
				southPanel.setLayout(new GridLayout(1,1));
				southPanel.add(btn3);
				panel.add(northPanel,"North");
				panel.add(southPanel,"South");
				break;
			case 3:
				northPanel.setLayout(new GridLayout(3,1));
				northPanel.add(btn1);
				northPanel.add(btn2);
				northPanel.add(btn3);
				panel.add(northPanel,"North");
				break;
		}
		
		return panel;
	}
	
	public JPanel createListPanel(JLabel[] labels) {
		
		int numberOfitems = labels.length;
		JPanel listPanel = new JPanel(new GridLayout(numberOfitems,1,4,4));
		
		for(int i=0;i<numberOfitems;i++) {
			listPanel.add(labels[i]);
		}
		
		return listPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("friend")) {
			cl.show(this.getContentPane(), "friend");
		}else if(e.getActionCommand().equals("stranger")) {
			cl.show(this.getContentPane(), "stranger");
		}else if(e.getActionCommand().equals("blacklist")) {
			cl.show(this.getContentPane(), "blacklist");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2) {
			if(e.getSource() instanceof JLabel) {
				JLabel jl = (JLabel)e.getSource();
				String getter = jl.getText().trim();
				System.out.println("You chat with "+getter);
				ChatWindow chatWindow = new ChatWindow(this.getSender(),getter, this.getUm());
				this.getUm().getCollectionOfChatWindows().put(this.getSender()+"+"+getter, chatWindow);
				
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel) e.getSource();
		jl.setForeground(Color.GREEN);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.BLACK);
	}
}
