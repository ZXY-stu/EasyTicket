<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>用户登陆</title>  
    <link rel="stylesheet" href="html/css/pintuer.css">
    <link rel="stylesheet" href="html/css/admin.css">
    <script src="html/js/jquery.js"></script>
    <script src="html/js/pintuer.js"></script> 
</head>
<body >
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form action="BussinessLogin" method="post">
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>后台登陆中心</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big"  id="account" name="phone" placeholder="登陆账户" data-validate="required:请输入帐户" />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" id="pwd" class="input input-big" name="pwd" placeholder="登陆密码" data-validate="required:请输入密码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                  
                </div>
                <div style="padding:30px;"><input type="submit"   class="button button-block bg-main text-big input-big" value="登陆"></div>
            </div>
            </form>          
        </div>
    </div>
</div>

</body>
</html>

</body>
</html>




