<%@ page import="java.io.*" %> 
<html>
<head>
<title>sd</title>
<script type="text/javascript">
function clock(){
	self.close();
}
</script>
</head>
<body>

<% 
String path = request.getRealPath("/");   
FileWriter fw = new FileWriter(path + "File.txt");
String mac = request.getParameter("mac");  
String ticketCode = request.getParameter("ticketCode");
String gatelocate =new String(request.getParameter("gateLocate").getBytes("iso-8859-1"),"utf-8");
String phone = request.getParameter("phone");
String content1 = "mac:"+mac;
String content2 = "ticketCode:"+ticketCode;
String content3 = "gatelocate:"+gatelocate;
String content4 = "phone:"+phone;
fw.write(path+"--");
fw.write(content1+"--"); 
fw.write(content2+"--"); 
fw.write(content3+"--"); 
fw.write(content4); 
fw.close();   
%> 

 </body>
 </html>