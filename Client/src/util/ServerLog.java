package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class ServerLog {
      private File logFile;
      public static String path;
      public static String filename;
     
      
      public ServerLog(String path,String filename){
    	  this.path = path;
    	  this.filename = filename;
    	  createFile(path,filename);
      }
      
      public static boolean  createFile(String path,String filename){
	    	File newDir = new File(path);
	    	File newFile = new File(path,filename);
	    	boolean newCreatDir = newDir.exists();
	    	boolean newCreatFile = newFile.exists();
	    	System.out.print(newCreatDir);
	        if(newCreatDir==false)
	    	try{
				newDir.mkdir();
				if(newCreatFile==false)
				newFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if(newDir.exists() && newFile.exists())
	        	return true;
	    	return false;
      }
      
      
 
      public static void updateLog(String conent) {    
          BufferedWriter out = null;    
          try {    
              out = new BufferedWriter(new OutputStreamWriter(
            		  new FileOutputStream(path+"\\"+filename, true)));    
              out.newLine();
              out.write(conent);
             
          } catch (Exception e) {    
              e.printStackTrace();    
          } finally {    
              try {    
                  if(out != null){ 
                      out.close();    
                  } 
              } catch (IOException e) {    
                  e.printStackTrace();    

              }    

          }    

      }    
      
      public static void updateLog(String file, String conent) {    
          BufferedWriter out = null;    
          try {    
              out = new BufferedWriter(new OutputStreamWriter(
            		  new FileOutputStream(file, true)));    
              out.newLine();
              out.write(conent);
             
          } catch (Exception e) {    
              e.printStackTrace();    
          } finally {    
              try {    
                  if(out != null){ 
                      out.close();    
                  } 
              } catch (IOException e) {    
                  e.printStackTrace();    

              }    

          }    

      }    

     
      
}
