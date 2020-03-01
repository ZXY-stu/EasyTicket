package com.device;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class DeviceServer extends JPanel implements Runnable {

    private final static int PORT = 5001;
    private ServerSocket server = null;
    private static DeviceServer httpServer;
    public  JFrame frame;
	  public  JLabel title;
	  public  JPanel panel;
	  public JLabel gate_1;
	  public JLabel gate_2;
	  public JLabel gate_3;
	  public JLabel gate_4;
	  public JLabel gate_1_text;
	  public JLabel gate_2_text;
	  public JLabel gate_3_text;
	  public JLabel gate_4_text;
  
    public static void builder(){
    	if(httpServer==null)
    		httpServer = new DeviceServer();
    }
    
    public  static void main(String[]args){
  
	 
	   builder();
	   javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               JFrame frame = new JFrame("闸机门模拟器");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.add(httpServer);
               frame.pack();
               frame.setSize(300, 300);
               frame.setVisible(true);
           }
       });
   }
    
    
    private DeviceServer() {
        try {

        	 setLayout(new GridLayout(0, 1));
        	 gate_1_text = new JLabel();
        	 gate_2_text = new JLabel();
        	 gate_3_text = new JLabel();
        	 gate_4_text = new JLabel();
      	   add(createRow_1(gate_1_text,"1号闸机门","关"));
      	 add(createRow_1(gate_2_text,"2号闸机门","关"));
      	add(createRow_1(gate_3_text,"3号闸机门","关"));
      	add(createRow_1(gate_4_text,"4号闸机门","关"));
            server = new ServerSocket(PORT);
  
            if (server == null)
                System.exit(1);
            new Thread(this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public JPanel createRow_1(JLabel label,String param,String value){
    	 JPanel j = new JPanel();
    	  j.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
      JLabel  gate_1 = new JLabel();
      
    	   JLabel  status = new JLabel();
    	    gate_1.setText(param);
    	    label.setText(value);
    	    status.setText("状态:");
    	    
    	    j.add(gate_1);
    	  
    	    j.add(status);
    	    j.add(label);
    	    return j;
     } 
     
     
     
    
    @Override
    public void run() {
        while (true) {
            try {  
            	System.out.println("设备已启动...");
                Socket client = null;
                client = server.accept();
                new Thread(new ServerThread(client)).start();
          } catch (Exception e) {
                 e.printStackTrace();
           }
        }
               
       
    }
    
     class ServerThread implements Runnable{
    	public  Socket socket;
    	private  BufferedReader reader;
    	private boolean isLogin;
    	private PrintWriter   out;
		public ServerThread(Socket socket){	
    		this.socket = socket;
    		  try {
				reader = new BufferedReader(
				          new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		
    	public void run(){
    		try{
    		  System.out.println("连接服务器成功！！...");
           
              out = new PrintWriter(new BufferedWriter(
  			new OutputStreamWriter(socket.getOutputStream())),true);
              // GET /test.jpg /HTTP1.1
           int i=0;
           Thread.sleep(1000);
	    		while(true){	
	    			 Thread.sleep(1);
	    			reciveMsg();
	    			
	    		}
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    	
       }
     
     
     private  void reciveMsg(){
    	 String str = null;
    	 while((str=read())!=null){
    		 if(str!=null)
    		 {
    			 String[] msg = str.split("-");
    			 final String[] gateLocateValue = msg[0].split(":");
    			 String[] statusValue = msg[1].split(":");
    			 
    			 switch(gateLocateValue[1]){
    			 case "1":
    				 gate_1_text.setText("开启");
    				new Thread(new Runnable(){
    					public void run(){
    						try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    						 gate_1_text.setText("关闭");
    					}
    					
    				}).start();
    			
    				 break;
    			 case "2":
    				 gate_2_text.setText("开启");
    					new Thread(new Runnable(){
        					public void run(){
        						try {
    								Thread.sleep(2000);
    							} catch (InterruptedException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
        						 gate_2_text.setText("关闭");
        					}
        					
        				}).start();
    				 break;
    			 case "3":
    				 gate_3_text.setText("开启");
    					new Thread(new Runnable(){
        					public void run(){
        						try {
    								Thread.sleep(2000);
    							} catch (InterruptedException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
        						 gate_3_text.setText("关闭");
        					}
        					
        				}).start();
    				 break;
    			 case "4":
    				 
    				 gate_4_text.setText("开启");
    					new Thread(new Runnable(){
        					public void run(){
        						try {
    								Thread.sleep(2000);
    							} catch (InterruptedException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
        						 gate_4_text.setText("关闭");
        					}
        					
        				}).start();
    				 break;
    			 }
 
  
    		 }
    	 }
     }
     
     private String read(){
    	 String str = null;
    	 try {
			str = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return str;
     }
     
 
     
     class MyRequest{
    	 HashMap<String, String> requset;
    	 public String getParameter(String param){
    		 return requset.get(param);
    	 }
     }
     

      
     private  void closeSocket(Socket socket) {
         try {
             socket.close();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
         System.out.println(socket + "离开了HTTP服务器");
     }
     }
     }