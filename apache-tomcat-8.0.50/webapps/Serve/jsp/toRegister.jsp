<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>用户注册</title>  
    <link rel="stylesheet" href="html/css/pintuer.css">
    <link rel="stylesheet" href="html/css/admin.css">
    <script src="html/js/jquery.js"></script>
    <script src="html/js/pintuer.js"></script> 
	<script>
	 function check(){
		 var p1 = document.getElementById("pwd").value;
		 var p2 = document.getElementById("pwd_again").value;
		 var cpw = document.getElementById("comfirm_pwd");
		 if(p1==p2)
			 comfirm_pwd.innerHTML="<font  color='green'>密码一致</font>";
		 else
			 comfirm_pwd.innerHTML="<font  color='red'>密码不同</font>";
			 
	 }
	function judgeRadioClicked() 
   { 
    var t = document.getElementById("comfirm_bus");
    var radios = document.getElementById("radios1");
     if(radios.checked){
		 
		 alert("sadsadsa");
	 }                               
  
    } 
	
	
  function myBlur(){
	  startCheck(this);
  }
  
  function myFocus(){
	  //startCheck(this);
  }
  
  window.onload = function(){
	var a =   document.getElementById("radios1");
	a.onfocus = myFocus;
	a.onblur = myBlur;
	  
  }
  
  function startCheck(oinput){
	 
	  var b = document.getElementById("place");
	  if(oinput.checked){
		  if(!b.value){
			  b.focus();
			  document.getElementById("comfirm_bus").innerHTML="<font  color='red'>商家必须输入</font>";
			  
		  }
		  else {
			
			  document.getElementById("comfirm_bus").innerHTML="123";
		  
		  }
	  }
	 
  }
  
  function startCheck_2(){
	  
	    document.getElementById("comfirm_bus").innerHTML="";
  }
	
	</script>
</head>
<body >
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form action="Register" method="post">
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>用户注册</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big"  id="userName" name="userName" placeholder="用户昵称"  data-validate="required:请输入昵称" />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" id="用户电话" class="input input-big" name="phone" placeholder="电话" data-validate="required:请输入电话" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
					
					 <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" id="sightId" class="input input-big" name="areaId" placeholder="景区ID" data-validate="required:景区ID" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
					 <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" id="pwd" class="input input-big" name="pwd" placeholder="登陆密码" data-validate="required:请输入密码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
					
					 <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" id="pwd_again" class="input input-big" name="pwd"  placeholder="确认密码" onkeyup="check()" />
                            <span id="comfirm_pwd"></span>
                        </div>
                    </div>
					
					
					
				 	 <div class="form-group">
					                        <div class="field field-icon-right">
											<p style="color:gray;font-size:16px">请选择用户类型：</p>
											</div>
                        <div class="field field-icon-right"  id="bussiness">
							<input type="radio" name="isBussiness" id="radios1" value="true"><span style="color:gray;font-size:15px">商家</span>
							<input type="radio" name="isBussiness"  value="false"><span style="color:gray;font-size:15px">买家</span>
                        </div>
                    </div>
					   
				
					
					 <div class="form-group" id="locate">
                        <div class="field field-icon-right">
							<input type="text" name="locate" id="place" class="input input-big" placeholder="请输入公司地点,买家可不填"  onChange="startCheck_2()"/>
							<span id="comfirm_bus"></span>
                        </div>
                    </div>
                  
				 
					
						   
					 <div class="form-group">
					                        <div class="field field-icon-right">
											<p style="color:gray;font-size:16px">性别：</p>
											</div>
                        <div class="field field-icon-right">
							<input type="radio" name="sex" value="m"><span style="color:gray;font-size:15px">男</span>
							<input type="radio" name="sex" value="w"><span style="color:gray;font-size:15px">女</span>
                        </div>
                    </div>
					
					
                </div>
                <div style="padding:30px;"><input type="submit"   class="button button-block bg-main text-big input-big" value="注册"></div>
            </div>
            </form>          
        </div>
    </div>
</div>

</body>
</html>

</body>
</html>




