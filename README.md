# 基于MAC地址识别技术的验票系统

## 项目背景：
  -  随着互联网技术的飞速发展，传统的纸质门票逐渐被更加高效、便利的、易于管理的电子门票所取代。可现如今存在的一些电子门票技术如RFID、NFC等识别技术往往无法满足各方面的需求。地铁、景区等场所排队难、取票难等问题依然层出不穷。尽管有最新推出的二维码电子门票技术，可其构建成本较高以及背后的安全性问题更是不容小觑。

## 项目目的：
  
 - 初衷是为了设计一套更加安全，便捷，低成本的包括手机app，服务器以及检票端的票务系统。
 - RFID门票构建成本不低，而且门票易丢失，且需要排队取票。总体来说用户体验好感度不高。
 - 人脸识别技术，好处毋庸置疑。但是构建维护成本高。数据量庞大，短时间内难以普及。
 - 二维码门票识别技术。作为一种目前来说更为流行的技术优势也是比较明显的，但其构建成本不低，同时该技术背后的安全问题也是值得重视的方面。
 - 基于MAC地址门票识别技术，它具有极低的构建和转化成本，只需要在系统终端安装一个无线网卡，安装对应的票务系统即可实现与原有接口无缝对接，方便快捷。同时该技术作为一种新型的门票识别技术，用户使用后将会有着与众不同的体验感。

## 项目完成时间：
 - 大学学习之余，历时近一年多的时间完成了方案的设计，系统模型的设计以及实现。

## 项目内容：
 - 主要包括三大板块。服务器、手机app、商家检票端的实现。
 -  第一是服务器后台开发，apache+mysql+servlet实现与手机app和商家检票端的通信和业务逻辑处理。
 -  第二是Android app软件，主要实现了用户注册，登录，WIFI门票的激活，识别和使用等等核心功能。 
 -  第三是商家检票端，实现了核心功能包括读取用户连接热点的实时MAC地址(采用bat批处理命令实时读取网卡的热点信息)，识别和验证用户MAC地址的真实性，实现虚拟闸机门的开启和关闭状态，实现了多用户同时登录使用门票并正确快速识别不同用户的MAC地址门票信息，完成门票验证的工作。
  
## 系统的使用介绍：
 - 我提供了一个视频Demo,简单展示了系统的使用过程。
 - 左边部分是手机app端的一个简单登陆 + 门票激活->激活成功->门票使用的过程。   
 - 门票激活 = 选择闸机门编号+发送门票相关消息给检票端，检票端返回热点SSID+热点pwd,然后手机连接检票端热点完成门票激活。
 - 右边部分模拟了现实景区或者地铁的一个闸机门示意图，一共有四个闸机门。若用户选择的是第3个闸机门，那么点击门票使用的时候，第三个闸机门将开启。
 - 门票使用 = 断开与检票端热点连接 + 检票端检测断开用户是否为指定用户 + 开启对应闸机门 
