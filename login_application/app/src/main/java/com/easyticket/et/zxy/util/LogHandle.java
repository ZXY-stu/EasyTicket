package com.easyticket.et.zxy.util;

/**
 * Created by Administrator on 2018/4/10.
 */

import android.util.Log;

/**
 * Created by Administrator on 2017/9/16.
 */

public class LogHandle {

    private static String LOGTAG_D = "MVP_D";

    private static String LOGTAG_E = "MVP_E";

    public  static void d(String msg){
        Log.d(LOGTAG_D,msg);
    }

    public  static void e(String msg){
        Log.d(LOGTAG_E,msg);
    }
}

