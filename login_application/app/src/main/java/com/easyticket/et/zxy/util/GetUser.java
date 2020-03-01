package com.easyticket.et.zxy.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2018/4/9.
 */

public  class GetUser {
    private static String Url ="http://192.168.0.103:8080/Server/";
    public static String url(){return Url; }
    public   static void    setUrl(String ip){ Url = "http://"+ip+":8080/Server/"; }
}

