package com.easyticket.et.zxy.util;

import android.content.Context;
import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import  com.easyticket.et.zxy.data.AppStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    /*
    public static boolean handleWifiInfoResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
*/
       public static AppStore.WifiInfo handleUseTicketResponse(String response) {
           AppStore.WifiInfo wifiInfo = new AppStore.WifiInfo();
           if (!TextUtils.isEmpty(response)) {
               try {
                   JSONObject jsonObject = null;
                   JSONArray wifiinfo = new JSONArray(response);
                   for (int i = 0; i < wifiinfo.length(); i++) {
                      jsonObject = wifiinfo.getJSONObject(i);
                       wifiInfo.setSsid(jsonObject.getString("ssid"));
                       wifiInfo.setPwd(jsonObject.getString("pwd"));
                       wifiInfo.setGateNums(jsonObject.getInt("gateNums"));
                       wifiInfo.setName(jsonObject.getString("name"));
                   }
                   if(wifiinfo.length()>0 && jsonObject!=null) {
                       String ssid = wifiInfo.getSsid();
                       if(ssid!=null && ssid.length()>0)
                       return wifiInfo;
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       return null;
       }





        public static List<AppStore.Ticket> handleTicketResponse(String response) {
        List<AppStore.Ticket> ticket = new ArrayList<AppStore.Ticket>();
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray allTickets = new JSONArray(response);
                for (int i = 0; i < allTickets.length(); i++) {
                    JSONObject ticketObject = allTickets.getJSONObject(i);

                    String name = getStringData(ticketObject, "name");
                    String ticketCode = getStringData(ticketObject, "ticketCode");
                    double price = Double.parseDouble(getStringData(ticketObject, "price"));
                    String briefIntroduction = getStringData(ticketObject, "briefIntroduction");
                    double grade = Double.parseDouble(getStringData(ticketObject, "grade"));
                    String imagePath = getStringData(ticketObject, "imagePath");
                    String sales_number = getStringData(ticketObject, "sales_number");
                    String labels = getStringData(ticketObject, "labels");
                    String locate = getStringData(ticketObject, "locate");
                    int num = Integer.parseInt(getStringData(ticketObject, "num"));
                    String dealTime = getStringData(ticketObject, "dealTime");
                    String original_place = getStringData(ticketObject, "original_place");
                    String destination_place = getStringData(ticketObject, "destination_place");
                    String dealLine = getStringData(ticketObject, "dealLine");
                    int ticketType = Integer.parseInt(getStringData(ticketObject, "ticketType"));
                    String areaid = getStringData(ticketObject, "areaId");
                    String payStatus = getStringData(ticketObject,"payStatus");
                    int gatesNum = Integer.parseInt(getStringData(ticketObject,"gatesNum"));

                    ticket.add(new AppStore.Ticket(name, ticketCode, price, briefIntroduction, grade,
                            imagePath, sales_number, labels, locate, num, dealTime, original_place,
                            gatesNum,destination_place,dealLine,ticketType,
                            areaid,payStatus));
                }
                return ticket;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    private static String getStringData(JSONObject object,String name) throws JSONException {
        return AnalysisData.analysisData(object.getString(name));
    }

    private static String getStringPwd(JSONObject object,String name) throws JSONException {
        return AnalysisData.analysisPwd(object.getString(name));
    }
    private static String getStringPhone(JSONObject object,String name) throws JSONException {
        return AnalysisData.analysisPhone(object.getString(name));
    }

    private static String getStringUser(JSONObject object,String name) throws JSONException {
        return AnalysisData.analysisUsr(object.getString(name));
    }

    private static String getStringSsid(JSONObject object,String name) throws JSONException {
        return AnalysisData.analysisSsid(object.getString(name));
    }
    /**
     * 将返回的JSON数据解析成Weather实体类
     */

    public static  String  handleLogin(String response,Context context) {
     String status  = null;
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray login = new JSONArray(response);
                for (int i = 0; i < login.length(); i++) {
                    JSONObject ticketObject = login.getJSONObject(i);
                    status = ticketObject.getString("serverMsg");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return status;
    }

}
