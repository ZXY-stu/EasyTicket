package util;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.*;

import com.Client;
public class Connector{
	public static void main(String[]args)throws IOException{
      
	  ConnectToServer ctc =  new ConnectToServer("127.0.0.1",5001);
	   new Thread(ctc).start();
 
        PrintStream p = new PrintStream(ctc.getSocket().getOutputStream());
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String str = null;
		
	   while((str=br.readLine())!=null ){
	
	
		   ConnectToServer.cmd = str;
		    p.println(str);
		   if(str.equals("#q")){
			   break;
		   }
		   
		
	   }
		
	   
	   

}
}
class ConnectToServer implements Runnable{
	private String msg,ipAddr;
	private int port;
	public static String cmd = "#ssd";
	private Socket cilent;
	private PrintWriter out;
	private BufferedReader in;
	private boolean Quit = false;
	public static final Scanner 	input = new Scanner(System.in);
	public ConnectToServer(String ipAddr,int port)
	{
	
		this.ipAddr = ipAddr;
		this.port = port;
		try{
			
		 	cilent = new Socket(ipAddr,port);
		 
		}catch(Exception e){	
		}
	}
	
	public String getMsg(){
		return msg;
	}
	
	public  void disconnect(){
		Quit = true;
	    try{
		out.close();
		in.close();
		cilent.close();
		
		}catch(IOException e){}
		System.out.println("成功断开连接！");
	}
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	public String getCmd(){
		return cmd;
	}
	
	public void  setCmd(String cmd){
		this.cmd = cmd;
	}
	
	public void sendMsg(){
		out.println(msg);
	}
	
	public Socket  getSocket(){
		return cilent;
	}
	
	public void receiveMsg(String msg){
		this.msg = msg;
	}
	
	
	public  void run(){
	   while(!Quit){
		   try{
			   try{
				
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cilent.getOutputStream())),true);
		in = new BufferedReader(new InputStreamReader(cilent.getInputStream()));
				   
		}catch(UnknownHostException ue){
			System.out.println("未找到该地址："+ipAddr);
		}catch(IOException i){
			i.printStackTrace();
			System.out.println(cilent);
		}catch(Exception e){
			e.printStackTrace();
			
		}
			   
		  switch(cmd){
			  case "#s":{
	              setMsg(input.next());
				  sendMsg();
			  }
			  break;
			  case "#q":
			  disconnect();
			  break;
		   default:
		     receiveMsg(in.readLine());
			 System.out.println("receive from Server Msg：");
			 System.out.println(msg+cilent);
		  }
		   }
      catch(IOException e){
	  }		  
	   }
	}
}