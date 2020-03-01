package util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.Client;

import beans.ServerMsg;


/**
 * 一个简单的HTTP客户端，的作用
 * 和httpUrlConnection,HttpClient 
 * ,AsycnHttpClient,
 * xutils中httpUtils是一样的。
 * 发送HTTP请求，模拟浏览器
 * 可打印服务器发送过来的HTTP消息
 */
public class ServerConnector {
	private  String encoding = "GBK";
    private  boolean Quit;
    public static boolean IsloadUseTicketInfoMsg;
    public static  String cmd;
    public   String content;
    private  String ip;
    private  int port;
    private Socket socket;
    private Queue<HashMap<String, String>>  serverMsg;
    private static HashMap<String, String> useTicketInfo;
    public ServerConnector(String ip,int port){
			this.ip = ip;
			this.port = port;
			serverMsg = new LinkedList<HashMap<String, String>>();
         
    }
    
    public Socket getSocket(){
    	return socket;
    }
    
    public void start(){
    	  new Thread(new ClientThread()).start();
    }

	public static HashMap<String, String> getUseTicketInfo() {
		IsloadUseTicketInfoMsg = false;
		return useTicketInfo;
	}

	public  void setUseTicketInfo(HashMap<String, String> useTicketInfo) {
		IsloadUseTicketInfoMsg = true;
		this.useTicketInfo = useTicketInfo;
	}
	
	public static void sendMsgToConnector(String msg){
		cmd = msg;
	}

	
	class ClientThread implements Runnable{
		String content = null;
		public   BufferedReader reader;
		public  PrintWriter  	out;
		public ClientThread(){
			try {
				try {
					socket = new Socket(ip, port);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				reader  = new BufferedReader(new 
				InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(new BufferedWriter(new
				OutputStreamWriter(socket.getOutputStream())),true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		

		public void reciveMsg(){
			
			if(socket!=null)
			try {
			String str = null;	
		    List<String> msg = new ArrayList<String>();
		    String key = null;
			while((str=reader.readLine())!=null){
				sendMsgToFace(str+"  "+key);
				HashMap<String, String> temp = 
				GetMsgTo.hash(str);
				key = GetMsgTo.getKey();
				if(ServerMsg.UseTicketInfo.equals(key)){
					//检查这里的数据变化情况
					//检查这里的数据变化情况
					//检查这里的数据变化情况
					setUseTicketInfo(temp);   //检查这里的数据变化情况
					//检查这里的数据变化情况
				}else if(str.contains("?")){
					 String[] temp1= str.split("\\?");
					 String[] status = temp1[1].split("=");
					 System.out.print(status[0] + status[1]);
					 if("1".equals(status[1])){
						Client.LOGIN = true;
						System.out.print(Client.LOGIN);
					 }
					 
					
						 
				  }
				
				
				
			
			}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void sendMsgToFace(String msg){
			System.out.println(msg);
		}
		
		public void sendMsg(String msg){
			out.println(msg);
		}
		
		public void sendHttpRequest(String url,String sendData)
		{
			StringBuffer sb = new StringBuffer();
			sb.append("GET "+url+" HTTP/1.1\r\n");
			sb.append("Host: "+ip+"\r\n");
			sb.append("Connection: Keep-Alive\r\n");
			sb.append("user-agent: 1601\r\n");
			sb.append("\r\n");
			out.println(sb.toString());
		}
		
		
		public void run(){
	
			 int i=0;
			while(true){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(cmd!=null)
				{
				 String[] cmds = cmd.split(":");
				switch(cmds[0]){
				case "Login":			   	
				//content = "/ClientLogin?phone=1234567892&pwd=123456";					
				sendHttpRequest(cmds[1], null);
				cmd = "receiveOrSend";
				break;
				case "Logout":
					sendHttpRequest(cmds[1], null);
					cmd = "receiveOrSend";
					break;
				default:	
					reciveMsg();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					//sendMsg("123456"+i++);
					
				//	System.out.print(IsloadUseTicketInfoMsg);
				}			
			  }
			
		}
		}
	}
	

}