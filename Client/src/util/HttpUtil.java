package util;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;



/**
* @author www.baizeju.com
*/
public class HttpUtil{
    private static String ENCODE = "GBK";
    private static String message( String url ) {
    	Runtime rt = Runtime.getRuntime();
        Process process = null;
      
        try {
            process = rt.exec("H:/课题-电子通行票/workspace/TicketClient/phantomjs-2.1.1-windows/bin/phantomjs.exe H:/课题-电子通行票/workspace/TicketClient/parse.js " +url);
            InputStream in = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sbf = new StringBuffer();
            String tmp = "";
            
            while ((tmp = br.readLine()) != null) {
                sbf.append(tmp);
            }
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return url;
       
    }
    
   public static void main(String[] args) {
	   while(true){
	   String msg = message("http://localhost:8080/TicketServer/html/a.html");
	   System.out.print(msg);
	   }
    }
}