var init = function(){
         
	   /*  var account = document.getElementById("account");
		 var pwd = document.getElementById("pwd");
         var gateLocate = document.getElementById("gateLocate");
		 var ticketCode = document.getElementById("ticketCode");
		 var mac = document.getElementById("mac");
		 var forms = document.getElementById("forms");
		 var phone = document.getElementById("phone");*/
		 
		JS.Engine.on('start',function(cId,channelList,engine){
			alert("连接已建立，连接id为"+cId);
		});
	     	
		
		 JS.Engine.on({
               ticket : function(kb){//侦听一个channel
			        // alert(kb);
                   //  mac.innerHTML = kb;
					 
					// ticketCode.innerHTML = kb.ticketCode;
					// gateLocate.innerHTML = kb.gateLocate;
					// phone.innerHTML = kb.phone;
					 
					
					 
					// forms.submit();
                }
        });
		
        JS.Engine.start('conn');
		//setCookie("pwd",pwd.value,1);
		//setCookie("phone",account.value,1);
		
		
}


// 设置Cookie
function setCookie(name, value, expireDay) {
	var exp = new Date();
	exp.setTime(exp.getTime() + expireDay * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + encodeURIComponent(value) + ";expires="
			+ exp.toGMTString();
}
