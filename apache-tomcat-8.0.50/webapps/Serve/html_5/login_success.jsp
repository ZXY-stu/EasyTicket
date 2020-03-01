<%@ page language="java" contentType="text/html;charset=utf-8" 
   pageEncoding="utf-8" %>

<%
  String name = request.getParameter("phone");
  String pwd = request.getParameter("pwd");
  
  if(name!=null && pwd!=null){
	 session.setAttribute("user",name);
     session.setMaxInactiveInterval(60);
	 response.sendRedirect("Home");
            return;        
  }
%>