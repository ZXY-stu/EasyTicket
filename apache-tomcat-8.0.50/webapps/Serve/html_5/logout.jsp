<%@ page language="java" contentType="text/html;charset=utf-8" 
   pageEncoding="utf-8" %>

<%
  session.removeAttribute("user"); 
session.invalidate();
 out.print("<script type='text/javaScript'>window.history.back(-1);</script>");
 
	 response.sendRedirect("../Home");  
%>
