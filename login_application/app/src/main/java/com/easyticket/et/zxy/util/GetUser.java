package com.easyticket.et.zxy.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.easyticket.et.zxy.activity.SetIpActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/9.
 */

public  class GetUser {
    public  static String Url ="http://192.168.0.103:8080/Server/";
    public  static  String pattern = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
    public static void  init(Context context){
       String T =  Data.get("ServerIp",context);
        Pattern r = Pattern.compile(pattern);
        Toast.makeText(context,T,Toast.LENGTH_LONG).show();
        Matcher m = r.matcher(T);
       if(m.matches()){
           Url = "http://"+T+":8080/Server/";
       }
        else {
           // Toast.makeText("context","请设置正确的服务器IP地址",1).show();
           Toast.makeText(context,"请设置正确的服务器IP地址",Toast.LENGTH_LONG).show();
           context.startActivity(new Intent(context, SetIpActivity.class));
       }
    }

    public static String url(){ return Url; }
    public   static void    setUrl(String ip,Context context){
       Data.put("ServerIp",ip,context);
        Url = "http://"+ip+":8080/Server/";
    }
}

