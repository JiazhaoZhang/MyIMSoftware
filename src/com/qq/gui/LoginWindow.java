package com.qq.gui;

import javax.swing.*;

import com.qq.common.*;
import com.qq.model.*;

import java.awt.*;
import java.awt.event.*;

/**
 * This is the log in window of my software
 * @author Zhang
 *
 */
public class LoginWindow extends JFrame implements ActionListener{
	
	JLabel northLabel;
	ImageIcon northImage;
	
	JButton loginButton, cancelButton;
	JPanel southPanel;
	
	JTabbedPane centerPane;
	
	JPanel centerJp1,centerJp2,centerJp3;
	JLabel nameLabel, passwdLabel, forgetName, forgetPasswd, help;
	JTextField namefield;
	JPasswordField passwdField;
	JCheckBox remember, hideMe;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginWindow lw = new LoginWindow();
	}
	
	public LoginWindow() {
		
		//define the NORTH
		northImage = new ImageIcon("image/background.jpg");
		northImage.setImage(northImage.getImage().getScaledInstance(400, 50, Image.SCALE_DEFAULT));
		northLabel = new JLabel(northImage);
		this.add(northLabel,"North");
		
		//define the SOUTH
		loginButton = new JButton("Log in");
		loginButton.addActionListener(this);
		loginButton.setActionCommand("login");
		cancelButton = new JButton("Cancel");
		southPanel = new JPanel(new FlowLayout());
		southPanel.add(loginButton);
		southPanel.add(cancelButton);
		this.add(southPanel,"South");
		
		//define the CENTER
		centerJp1 = new JPanel(new GridLayout(3,3));
		nameLabel = new JLabel("username",JLabel.CENTER);
		passwdLabel = new JLabel("password",JLabel.CENTER);
		namefield = new JTextField(15);
		passwdField = new JPasswordField(15);
		forgetName = new JLabel("Forget username",JLabel.CENTER);
		forgetPasswd = new JLabel("Forget password",JLabel.CENTER);
		remember = new JCheckBox("remember user");
		hideMe = new JCheckBox("invisible login");
		help = new JLabel("HELP");
		
		centerJp1.add(nameLabel);
		centerJp1.add(namefield);
		centerJp1.add(forgetName);
		centerJp1.add(passwdLabel);
		centerJp1.add(passwdField);
		centerJp1.add(forgetPasswd);
		centerJp1.add(remember);
		centerJp1.add(hideMe);
		centerJp1.add(help);
		
		centerJp2 = new JPanel();
		centerJp3 = new JPanel();
		
		centerPane = new JTabbedPane();
		centerPane.add("Username Log in",centerJp1);
		centerPane.add("Email Log in",centerJp2);
		centerPane.add("Cellphone Log in",centerJp3);
		
		this.add(centerPane,"Center");
		
		this.setVisible(true);
		this.setSize(400, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("login")) {
			
			User u = new User(this.namefield.getText().trim(),new String(this.passwdField.getPassword()).trim());
			MyConnector mc = new MyConnector("127.0.0.1",9999);
			ClientConnectThread cct = new ClientConnectThread(mc.getSk());
			UserManager um = new UserManager(mc,cct);
			
			
			System.out.println("Username is "+u.getUserID());
			System.out.println("Password is "+u.getPassword());
			
			boolean islegel = um.validateUser(u);
			
			if(islegel) {
				System.out.println("Log in successfully");
				new MainWindow(this.namefield.getText().trim(),um);
				um.startClientConnectThread();
				this.dispose();
			}else {
				JOptionPane.showConfirmDialog(this, "Wrong", "Invalid", JOptionPane.DEFAULT_OPTION);
			}
				
		}
	}
}
