package util;


import java.io.BufferedReader;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import javax.script.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.*;
import util.ServerConnector;

public class CheckMac
{
  private double starttime;
 public static  double proTime = 0.0D;
  private static boolean Quit;
  public static String UL;  //已连接客户端列表,为线性表
  public static HashMap<String,HashMap<String, String>> RUAL;   //源客户端MAC地址列表(RUAL)
  public static Map<String, Integer> DAL;  //动态客户端MAC地址列表(DAL)
  public static Queue<Map.Entry<String,HashMap<String, String>>>  DRL; //闸机门预开启地址列表(DRL)
  
  private Check check;
  private ConnectToGates connectToGates;
  public CheckMac(String ip,int port)
  {
    check = new Check();
    connectToGates = new ConnectToGates(ip, port);
    this.DAL =  Collections.synchronizedMap(new HashMap<String,Integer>());  
   // DAL = new HashMap<String,Integer>();
    this.RUAL = new HashMap<String,HashMap<String, String>>();
    this.DRL = new LinkedList<Map.Entry<String,HashMap<String, String>>>();
  }
  
  public void start(){
	  new Thread(check).start();
	  new Thread(connectToGates).start();
  }
  
  public void setApSsidPwd(String command){
	  FileWriter fw=null;
	  try {
	   fw=new FileWriter("bat/setApSsidPwd.bat");
	   fw.write(command);
	  } catch (IOException e) {
	   e.printStackTrace();
	   System.exit(0);
	  }finally{
	   if(fw!=null){
		try {
		 fw.close();
		} catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 System.exit(0);
		}
	   }
	  }
	 }
  
	class Check implements Runnable{
		
	  public void updateResourseMac(){  
	  //更新源mac地址   源mac地址指的是  
	  //手机客户端主动发送给检票端，
	  //为检票端提供验证的mac地址
	  new Thread(new Runnable(){
		  
		@Override
		public void run() {
			HashMap<String, String> newMac 
		= ServerConnector.getUseTicketInfo();
			
			try {			
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			  String mac = newMac.get("mac");
			  RUAL.put(mac, newMac);
		 }
		
	     }).start();
        }
		  
		  public boolean updateResourseMac(String mac, HashMap<String, String> newMac){
			//  HashMap<String, String> newMac = ServerConnector.getUseTicketInfo();
			  //String mac = newMac.get("mac");
			  if(RUAL.put(mac, newMac)!=null)
			  return true;
			  return false;
		  }
		  
		 // 2c:57:31:8b:e0:b6
		  
		  
		  public  Queue<Map.Entry<String,HashMap<String, String>>> 
		  checkTicketUse(HashMap<String,HashMap<String, String>> data,
		  Map<String, Integer> mac)
		  {
			 int gateLocate = 0;
			 //可能同时有多个用户使用门票，所以需要把所有不存在的值，全部压进队列
			 Queue<Map.Entry<String,HashMap<String, String>>> temp 
			 = new LinkedList<Map.Entry<String,HashMap<String, String>>>();
			 boolean isFind = false;  
		    for (Map.Entry<String,HashMap<String, String>> t : data.entrySet()) {
		      if (!mac.containsKey(t.getKey())){
		        data.remove(t.getKey());
		        System.out.println("res"+t.getKey()+t.getKey().length());
		        temp.add(t);
				
		      }	
		    }
		    return temp;
		  }

		  
		  
		private String execute(String batname)
		  {
		    Date data = null;
		    String s = null;
		    PrintWriter out = null;
		    Process clearMac = null;
		    Process startAp = null;
		    Process setSsidPwd = null;
		    Process getMacAddress = null;
		    boolean isadd = false;
		    int lines = 0;
		    int count = 0;
		    double time = 0.0D;
		    double starttime = System.currentTimeMillis();
		  
		    try
		    {
		      data = new Date();
		      clearMac = Runtime.getRuntime().exec("cmd /c start clearMac.bat", null, new File("bat"));
		      clearMac.waitFor();
		      setSsidPwd = Runtime.getRuntime().exec("cmd /c start setApSsidPwd.bat", null, new File("bat"));
		      setSsidPwd.waitFor();
		      startAp = Runtime.getRuntime().exec("cmd /c start startAp.bat", null, new File("bat"));
		      startAp.waitFor();
		      getMacAddress = Runtime.getRuntime().exec("cmd /c start getApConMac.bat", null, new File("bat"));
		      getMacAddress.waitFor();
		      
		      
		      File file = new File("deviceInfo//apMac.txt");
		      file.createNewFile();
		      FileReader fileReader = new FileReader(file);
		      LineNumberReader reader = new LineNumberReader(fileReader);
		      
		      out = new PrintWriter("deviceInfo//endTime.txt");
		      int pre_size= 0;
		    
		      
		      // 2c:57:31:8b:e0:b6
		      /*HashMap<String,String> newMac = new HashMap<String,String>();
		      newMac.put("mac", "2c:57:31:8b:e0:b6");
		      newMac.put("gateLocate","1");
		      newMac.put("phone","15574956069");
		      newMac.put("userName","zxy");
		      updateResourseMac("2c:57:31:8b:e0:b6", newMac);*/
		      while(!Quit)
		      {
		        Thread.sleep(10);
		        data.setTime(System.currentTimeMillis());
		        out.println(data.toString());
		        out.close();
		        int size = 0;
		        
		        
		        if(ServerConnector.IsloadUseTicketInfoMsg){
		        	ServerConnector.IsloadUseTicketInfoMsg = false;
		        	updateResourseMac();
		        }
	        	
				/*把当前检测到的mac地址列表存入DAL中*/
		        /* *
		         *  
		          存储在文本文档中的mac地址的格式为:
			
					
						存在连接时
						start 
						2c:57:31:8b:e0:b6
						3b:64:56:7b:0c:4c
					    1a:41:a6:c4:07:6a
						4c:7a:a1:8c:a0:b6
						end
						列表为空时：
						start
						end	
				 
				 
				 
		        * */
		        
		        do
		        {
		          reader.setLineNumber(lines);
		          UL = reader.readLine();
		          /*读取文本中的mac地址，写入Mac中*/
		          if (((!"start".equals(UL)) && (!"end".equals(UL)) && (UL != null)) || (
		            (isadd) && (UL != null) && (!"start".equals(UL)) && (!"end".equals(UL))))
		          {
		            DAL.put(UL,DAL.size());		
		           System.out.print(UL);
		            lines++;
		            size++;
		            isadd = false; 
		          }
		          if ("start".equals(UL)) {
		            isadd = true;
		          }
		        } while (!"end".equals(UL));
		           
				   /*  开始check*/
		      
		        time = (System.currentTimeMillis() - starttime) / 1000.0D;
		       
		     
		          count++;
		          proTime = time / count;
		          /*获取已经使用了的门票的用户mac地址*/
		          Queue<Map.Entry<String,HashMap<String, String>>> t
		           = checkTicketUse(RUAL,DAL);   //比较源地址RUAL和动态地址DAL，
		                                                //获取已经点击使用的用户mac地址以及相关用户信息，并存放在t中
		          if(t!=null){
					  while(!t.isEmpty()) /*全部添加到DRL中*/ 
						  //DRL中存放的是已经使用了的用户设备mac地址，
						  //且将随时发送给闸机门，由闸机门设备识别并开启相关闸机门
		                DRL.add(t.poll());  
		               System.out.println(t.toString());
		          }   
		        if(pre_size>size) //如果有用户使用了门票，则重置MAC地址列表，防止出现重复开门情况
		          DAL=  Collections.synchronizedMap(new HashMap<String,Integer>());  
		        pre_size = size;
		     
		      }
		    }
		    catch (FileNotFoundException e)
		    {
		        e.printStackTrace();
		    }
		    catch (Exception e)
		    {
		      try
		      {
		        PrintWriter err = new PrintWriter("deviceInfo//log.txt");
		        err.println(e.toString());
		        err.println(data.toString());
		        err.println(s);
		        err.close();              
		        e.printStackTrace();
		      }
		      catch (Exception localException2) {}
		    }
		    return "fail";
		  }
		
		@Override
		public void run() {	
			execute(null);		
		}
	}
	
   class ConnectToGates implements Runnable{
            private Socket socket;
            private   String ip;
            private  int PORT;
            private   BufferedReader reader;
		    private   PrintWriter  	out;
            private    Queue<Msg> msgs;
            private boolean Quit;
            
            class Msg{
            	private int gateLocate;
            	private int status;
            	public Msg() {
					// TODO Auto-generated constructor stub
				}
            	
            	public String toString(){
            		return "gateLocate:"+gateLocate+"-"+"Status:"+status;
            	}
            	
				public Msg(int gateLocate, int status) {
					super();
					this.gateLocate = gateLocate;
					this.status = status;
				}
    
				

				public int getGateLocate() {
					return gateLocate;
				}
				public void setGateLocate(int gateLocate) {
					this.gateLocate = gateLocate;
				}
				public int getStatus() {
					return status;
				}
				public void setStatus(int status) {
					this.status = status;
				}
            	
            	
            }
            
		    
            public ConnectToGates(String ip,int port) {
            	this.ip = ip;
            	this.PORT = port;
            	try {
            		msgs =  new  LinkedList<Msg>();
				  socket = new Socket(ip, port);
					Quit = false;
					if(socket!=null){
					reader  = new BufferedReader(new 
					InputStreamReader(socket.getInputStream()));				
					out = new PrintWriter(new BufferedWriter(new
					OutputStreamWriter(socket.getOutputStream())),true);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            
            public ConnectToGates() {
        				
        	}
            
            public boolean init(){
            	return false;
            }
            
            public boolean reconnect(){
            	
            	try {
				socket = new Socket(ip, PORT);
				Quit = false;
				if(socket!=null){
				reader  = new BufferedReader(new 
				InputStreamReader(socket.getInputStream()));				
				out = new PrintWriter(new BufferedWriter(new
				OutputStreamWriter(socket.getOutputStream())),true);
				}
            	} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
            		System.out.println(e.getLocalizedMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getLocalizedMessage());
				}
            	if(socket!=null)
            		return true;
            		
            	return false;
            }
            
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i=0;
				while(!Quit){
						
					sendMsg();
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getLocalizedMessage());
						}	
					  //  reciveMsg();
						
					   
			
				}	
			}
			
			public void toMsgs(){
				Entry<String, HashMap<String, String>> msg = null;
				HashMap<String, String> temp = null;
				/*只要DRL中存在已经使用了的门票的mac地址，就马上告诉闸机门需要开门了*/
				while(!DRL.isEmpty()){
					msg = DRL.poll();
					temp = msg.getValue();
	               int gateLocate = Integer.parseInt(temp.get("gateLocate"));     
	               msgs.add(new Msg(gateLocate,1));
				   sendMsgToFace(msg);
				}
			}
			
			public void sendMsgToFace(Map.Entry<String,HashMap<String, String>> msg){   //将用户数据传输到显示面板上
				System.out.println(msg.toString());
			}
			
			
			public void sendMsg(){
			toMsgs();
				while(!msgs.isEmpty()){
					out.println(msgs.poll().toString());
				}
				
				//out.println(new Msg(i,1).toString());
		
			}
			
			public String reciveMsg(){
				String msg = null;
				if(socket!=null)
				try {
					while((msg=reader.readLine()) != null){
			            System.out.println(msg);
					}
					System.out.println("msg");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getLocalizedMessage());
				}
				else{
				System.out.println("no device connected");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getLocalizedMessage());
				}
				reconnect();
				}
				return null;
			}
			
			

		}

}
