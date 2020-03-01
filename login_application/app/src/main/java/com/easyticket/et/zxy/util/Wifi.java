package com.easyticket.et.zxy.util;

/**
 * Created by zxy on 2018/3/15.
 */

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * Created by zxy on 2018/2/10.
 */

 public class Wifi {
    public static  WifiManager wifiManager;
    public static  boolean   isconnect;
    public static Handler mHandler;
    private String ssid,pwd;
    private  Context context;
    public static  Wifi wifi;
    private Wifi(Context context,String ssid, String password) {
        this(context);
        this.ssid = ssid;
        this.pwd = password;

    }

    private  Wifi(Context context){
        this.context = context;
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

     public  static Wifi builder(Context context,String ssid, String password){
        if(wifi ==null)
            wifi = new Wifi(context,ssid,password);
         return wifi;
     }

     public static Wifi builder(Context context){
         if(wifi ==null)
             wifi = new Wifi(context);
         return wifi;
     }

     public void setSsidPwd(String ssid,String pwd){
         this.pwd = pwd;
         this.ssid = ssid;
     }



    public void connect(){
        Thread t = new Thread(new Connect());
        t.start();
    }
    public class  Connect implements  Runnable {
        @Override
        public void run() {
            try {

                Thread.sleep(500);
                while (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                WifiConfiguration wifiConfig = createWifiInfo(ssid, pwd);
                int netID = wifiManager.addNetwork(wifiConfig);

               isconnect =  wifiManager.enableNetwork(netID, true);

                 wifiManager.reconnect();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {

        wifiManager.removeNetwork(wifiManager.getConnectionInfo().getNetworkId());
        wifiManager.disableNetwork(wifiManager.getConnectionInfo().getNetworkId());
    }

     public boolean  checkWifiConnect(){
         Class classType = WifiInfo.class;
         try{
             Object invo = classType.newInstance();
             Object result = invo.getClass().getMethod("getMeteredHint").invoke(invo);
             return (boolean)result;
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (InstantiationException e) {
             e.printStackTrace();
         } catch (NoSuchMethodException e) {
             e.printStackTrace();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         }
         return false;
     }

    private WifiConfiguration createWifiInfo(String SSID, String Password) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";

        WifiConfiguration tempConfig = this.IsExsits(SSID);
        if(tempConfig != null) {
            wifiManager.removeNetwork(tempConfig.networkId);
        }

        //默认就是wpa的加密方式
        config.preSharedKey = "\"" + Password + "\"";
        config.hiddenSSID = true;
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        config.status = WifiConfiguration.Status.ENABLED;
        return config;
    }

    // 打开wifi功能
    public void openWifi() {
        if(!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
    }
    public void closeWifi(){
        if(wifiManager.isWifiEnabled()) {
            disconnect();
          //  wifiManager.setWifiEnabled(false);
        }
    }


    private WifiConfiguration IsExsits(String SSID)
    {
        List<WifiConfiguration> existingConfigs = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs)
        {
            if (existingConfig.SSID.equals("\""+SSID+"\""))
            {
                return existingConfig;
            }
        }
        return null;
    }
}




