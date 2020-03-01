<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
	<script type="text/javascript" src="comet4j.js"></script>
	<script>
	function init(){
           var account = document.getElementById("account");
		 var pwd = document.getElementById("pwd");
         var gateLocate = document.getElementById("gateLocate");
		 var ticketCode = document.getElementById("ticketCode");
		 var mac = document.getElementById("mac");
		 var forms = document.getElementById("forms");
		 var phone = document.getElementById("phone");
		 
		JS.Engine.on('start',function(cId,channelList,engine){
			alert("连接已建立，连接id为"+cId);
		});
	     	forms.submit();
		
		 JS.Engine.on({
               ticket : function(kb){//侦听一个channel
			         alert(kb);
                    mac.innerHTML = kb;
					ticketCode.innerHTML = kb.ticketCode;
					 gateLocate.innerHTML = kb.gateLocate;
					phone.innerHTML = kb.phone; 
					forms.submit();
                }
        });
		
        JS.Engine.start('conn');
		setCookie("pwd",pwd.value,1);
		setCookie("phone",account.value,1);
		
		/*
 <span id="mac">...</span>
 <span id="phone">...</span>
 <span id="ticketCode">...</span>
 <span id="ticketType">...</span>*/
}


// 设置Cookie
function setCookie(name, value, expireDay) {
	var exp = new Date();
	exp.setTime(exp.getTime() + expireDay * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + encodeURIComponent(value) + ";expires="
			+ exp.toGMTString();
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
            <form action="" method="post">
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>后台管理中心</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big"  id="account" name="name" placeholder="登录账号" data-validate="required:请填写账号" />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" id="pwd" class="input input-big" name="password" placeholder="登录密码" data-validate="required:请填写密码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field">
                            <input type="text" class="input input-big" id="areaid" name="code" placeholder="填写areaId" data-validate="required:请填写右侧的验证码" />
                           <img src="images/passcode.jpg" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">  
                                                   
                        </div>
                    </div>
                </div>
                <div style="padding:30px;"><input type="button"  onClick="init()" class="button button-block bg-main text-big input-big" value="登录"></div>
            </div>
            </form>          
        </div>
    </div>
</div>


 <form action="DownTicketInfoFile"    id="forms" target="_blank">
 <input  type="hidden"  name="ticketCode" id="ticketCode" />
 <input  type="hidden" name="mac"  id="mac" />
 <input type="hidden"  name="phone" id="phone" />
  <input  type="hidden"   name="ticketType"  id="ticketType" /> 
 </form>


</body>
</html>




