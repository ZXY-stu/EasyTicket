<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Comet4J Hello World</title>
<script type="text/javascript" src="comet4j.js"></script>
<script type="text/javascript">
function init(){
        var kbDom = document.getElementById('kb');
		JS.Engine.on('start',function(cId,channelList,engine){
			alert("连接已建立，连接id为"+cId);
		});
       
		 JS.Engine.on({
               ticket : function(kb){//侦听一个channel
                        kbDom.innerHTML = kb;
                }
        });
		
		
        JS.Engine.start('conn');
	
			 setCookie("userName","zxy123",365);
			 setCookie("pwd","123456",123);
			 setCookie("phone","155775655089",123);
			 setCookie("areaid","4523654",365);
	
  

}



// 设置Cookie
function setCookie(name, value, expireDay) {
	var exp = new Date();
	exp.setTime(exp.getTime() + expireDay * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + encodeURIComponent(value) + ";expires="
			+ exp.toGMTString();
}
// 设置Cookie
function setCookie(name, value, expireDay) {
	var exp = new Date();
	exp.setTime(exp.getTime() + expireDay * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + encodeURIComponent(value) + ";expires="
			+ exp.toGMTString();
}
// 获得Cookie
function getCookie(name) {
	var arr = document.cookie
			.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null)
		return decodeURIComponent(arr[2]);
	return null;
}
// 删除Cookie
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}
// HTML编码
String.prototype.HTMLEncode = function() {
	var temp = document.createElement("div");
	(temp.textContent != null) ? (temp.textContent = this)
			: (temp.innerText = this);
	var output = temp.innerHTML;
	temp = null;
	return output;
};
// HTML解码
String.prototype.HTMLDecode = function() {
	var temp = document.createElement("div");
	temp.innerHTML = this;
	var output = temp.innerText || temp.textContent;
	temp = null;
	return output;
};
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, '');
};
</script>
</head>
<body onload="init()">


        剩余内存：<span id="kb">...</span>KB
</body>
</html>