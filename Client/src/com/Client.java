package com;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.CheckMac;
import util.ServerConnector;


public class Client implements Runnable{
	  public  JFrame frame;
	  public  JLabel speed;
	  public  JLabel mac;
	  public  JLabel speed_content;
	  public  JLabel mac_content;
	  public  JPanel panel;
      public JLabel gate_1;
      public JLabel gate_2;
      public JLabel gate_3;
      public JLabel gate_4;
      public JLabel gate_1_text;
      public JLabel gate_2_text;
      public JLabel gate_3_text;
      public JLabel gate_4_text;
      public JButton login;
      
	  private String serverIp;
      private int serverPort;
      private String deviceIp;
      private int  devicePort;
      private static CheckMac checkMac;
      private static ServerConnector serverConnector;
      private JDialog dialog;
      public static  boolean LOGIN;
      public Client(int serverPort,String serverIp,int devicePort,String deviceIp){
    	this.deviceIp = deviceIp;
    	this.devicePort = devicePort;
    	this.serverIp = serverIp;
    	this.serverPort = serverPort;
        serverConnector =
    	new ServerConnector(serverIp,serverPort);
    	checkMac = new CheckMac(deviceIp,devicePort);

	    dialog = new JDialog();
	    
	    this.panel = new JPanel();
	    this.panel.setLayout(new GridLayout(5,1));
	  
	   
	  
	    this.speed = new JLabel();
	    this.mac = new JLabel();
	    this.speed_content = new JLabel();
	    this.mac_content = new JLabel();
	    login = new JButton("«Îœ»µ«¬º£°");
	    login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == login){
				    serverConnector.start();
					dialog.add(new Login());
				}
			}
	 
	    });
	   
	    this.panel.add(this.speed);
	    this.panel.add(this.speed_content);
	    this.panel.add(this.mac);
	    this.panel.add(this.mac_content);
	    panel.add(login);
	    this.frame = new JFrame("…¡π∫ºÏ∆±œµÕ≥");
	    this.frame.setLocation(500,300);
	    this.frame.add(this.panel);
	    this.frame.pack();
	    this.frame.setDefaultCloseOperation(3);
	    this.frame.setVisible(true);
	   JFrame.setDefaultLookAndFeelDecorated(true);
	
	   
   }
      
      public static void start(){
    	  checkMac.start();
    	 serverConnector.start();
      }
      
      public void run(){
    	 boolean isStart = false;
    	  while(true){
    			try {
					Thread.sleep(30);
				}catch (InterruptedException e1) {
					e1.printStackTrace();
				}
    			if(LOGIN){
    				isStart = true;
    				LOGIN = false;
    				  checkMac.start();
    				  login.setVisible(false);
    			}
    	   if(isStart){
    		    speed_content.setText(String.format("time:%5.2f",checkMac.proTime));
          		mac_content.setText("read"+":"+ checkMac.DRL.toString());
          	 	speed.setText("mac:"+checkMac.DAL.toString()+" ");
          	 	mac.setText("resource"+checkMac.RUAL.toString());
    	   }
    	  }
      }
   
      
      public  static void main(String[]args){
    	    Client df = new Client(5001, "192.168.0.105",5001,"127.0.0.1");
    	    
    	    new Thread(df).start();
      }
}
