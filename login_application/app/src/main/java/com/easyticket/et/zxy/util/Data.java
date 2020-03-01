package com.easyticket.et.zxy.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by zxy on 2018/3/15.
 */

public class Data {
  public static void put(String params, String value, Context context) {
      SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
      editor.putString(params, value);
      editor.apply();
  }

    public static void delete(String params,Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.remove(params);
        editor.apply();
    }

    public static String get(String params,Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
         return prefs.getString(params,null);
    }
}
