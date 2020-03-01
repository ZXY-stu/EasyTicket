<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>成功</title>  
    <link rel="stylesheet" href="html/css/pintuer.css">
    <link rel="stylesheet" href="html/css/admin.css">
    <script src="html/js/jquery.js"></script>
    <script src="html/js/pintuer.js"></script> 
	<script>
	var i = 3;
	var	id = 0;
	function toLogin(){
	id=setInterval(delay,1000);
	}
	
	function delay(){
		var time = document.getElementById("time");
		i--;
        time.innerHTML=""+i;
		if(i==0){
	    clearInterval(id);  
		close();
		}
	}
	function close(){
		  js.toLogin();
		 alert("sdada");
	}
	</script>
</head>
<body onload="toLogin()">
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>       
            <div class="panel loginbox">
                <div class="text-center margin-big">
				<span>注册成功!</span><p>页面将在<span id="time">3</span>秒后跳转,若未跳转，请按手机返回键</p>
				  <div class="field field-icon-right">
		
				 </div>
		       
				</div>
				
			</div>  
       </div>
		  
        </div>
		
	
    </div>


</body>
</html>




