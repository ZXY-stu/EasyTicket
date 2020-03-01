package com.easyticket.et.zxy.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.View.XRecyclerView;
import com.easyticket.et.zxy.adpter.TicketAdapter;
import com.easyticket.et.zxy.data.AppStore;
import com.easyticket.et.zxy.data.ServerMsg;
import com.easyticket.et.zxy.data.UserInfo;
import com.easyticket.et.zxy.util.Data;
import com.easyticket.et.zxy.util.GetMac;
import com.easyticket.et.zxy.util.GetUser;
import com.easyticket.et.zxy.util.HttpUtil;
import com.easyticket.et.zxy.util.LogHandle;
import com.easyticket.et.zxy.util.ReadDate;
import com.easyticket.et.zxy.util.TitleBar;
import com.easyticket.et.zxy.util.Utility;
import com.easyticket.et.zxy.util.Wifi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class TicketActiveActivity extends BaseActivity{
    public final  int  ACTIVE_SUCCESS = 1;
    public final  int  RELOAD_DATA = 2;
    private TitleBar titlebar;
    private ImageButton active,use;
    private ArrayList<TextView> deal;
    private ImageView  ticket_picture;
    private TextView   ticket_name;
    private  AlertDialog dialog;
    private  ProgressDialog  progressDialog,progressDialog_success;
    private AppStore.User userInfo;
    private  AppStore.WifiInfo wifiInfo;
    private AppStore.UserTicket ticketActiveInfo;
    private String serverMsg;
    private  int gatelocate;
   private String[] gateNum;
    private String[] gatesName;
    public int delay;
    private Wifi wifi;
    private boolean haveRecived = false;   //为true的条件是场所的wifi名称和密码都已设置好
    private JSONObject json;
    private   Handler handler = new Handler(){
        //加入传消息来了就这么么办
        public void handleMessage(Message msg){
            switch(msg.what) {
                case  ACTIVE_SUCCESS: {

                    Toast.makeText(getApplicationContext(),wifi.checkWifiConnect() +wifiInfo.getSsid()+Wifi.wifiManager.getConnectionInfo().getMacAddress().toString()+GetMac.getMac(),Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    progressDialog_success.show();
                    break;
                }
                case RELOAD_DATA: {
                    progressDialog_success.dismiss();
                    reloadDate();

                }
            }
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        init(savedInstanceState);
        loadDate();
        dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.gates)//设置标题的图片
                .setTitle("请选择所在闸机门编号")//设置对话框的标题
                .setSingleChoiceItems(gatesName, 0,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gatelocate = which;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //设置发送激活门票的信息
                        setActiveInfo(getIntent().getStringExtra("ticketCode"),GetMac.getMac(),
                                gateNum[gatelocate], Data.get("phone",getApplicationContext()),Integer.parseInt(getIntent().getStringExtra("ticketType")),
                                getIntent().getStringExtra("areaid"));
                        dialog.dismiss();
                        //加载数据，发送数据至服务器，并接受服务器发来的数据

                        progressDialog.show();

                        Random random = new Random(System.currentTimeMillis());
                        //Random random1 = new Random(System.currentTimeMillis());
                        int  time = random.nextInt(200);
                        int time2 = random.nextInt(300);
                        delay = time + time2;
                        //执行数据的发送，以及数据的接收
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    int requsetNum = 0;

                                    while(true) {

                                        if(!haveRecived && requsetNum++<3) {
                                            Thread.sleep(delay);
                                            requestUseTicket(ticketActiveInfo);
                                        }
                                        if(requsetNum>=3) {
                                            break;
                                        }
                                        if(haveRecived) {

                                            sendStatus(ticketActiveInfo);


                                            if (serverMsg!=null && ServerMsg.UseTicketSuccess.equals(serverMsg)) {
                                                serverMsg = null;
                                                wifi.setSsidPwd(wifiInfo.getSsid(), wifiInfo.getPwd());

                                                wifi.connect();
                                                //"DISCONNECTED"
                                                Thread.sleep(4500);
                                                if ( Wifi.isconnect) {
                                                    haveRecived = false;
                                                     Wifi.isconnect = false;
                                                    handler.sendEmptyMessage(ACTIVE_SUCCESS);  //发送激活成功msg
                                                    Thread.sleep(300);
                                                    handler.sendEmptyMessage(RELOAD_DATA);
                                                    break;
                                                }
                                            }
                                        }
                                        Thread.sleep(delay*2);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                }).create();
        //设置按钮监听，点击激活门票
        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击激活后，要求用户选择所处闸机门编号
                dialog.show();
                Window window = dialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha = 0.7f;
                window.setAttributes(lp);

            }
        });

        use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random(System.currentTimeMillis());
                int time = r.nextInt(100);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                use();
            }
        });
    }



    public void requestUseTicket(final AppStore.UserTicket userTicket) {
   final     String Url =  GetUser.url()+"UseTicket?phone=" + userTicket.getPhone()+"&ticketCode="+userTicket.getTicketCode()
                +"&mac="+userTicket.getMacAddress()+"&gateLocate="+userTicket.getGateLocate()+"&areaId="+userTicket.getAreaid();

        HttpUtil.sendOkHttpRequest(Url,
                new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseText = response.body().string();
                        wifiInfo = Utility.handleUseTicketResponse(responseText);


                     runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (wifiInfo != null) {
                                    haveRecived = true;
                                    Toast.makeText(getApplicationContext(), wifiInfo.getSsid(), 1).show();
                                }else
                                    Toast.makeText(getApplicationContext(), wifiInfo+" "+Url, 1).show();


                            }
                        });
                    }





                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                        Toast.makeText(getApplicationContext(),"获取数据失败，请检查网络！",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
    }


    public void sendStatus(final AppStore.UserTicket userTicket) {
        String Url =  GetUser.url()+"UseTicketSuccess?phone="+userTicket.getPhone()
                +"&ticketCode="+userTicket.getTicketCode();
        HttpUtil.sendOkHttpRequest(Url,
                new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseText = response.body().string();
                        if (!TextUtils.isEmpty(responseText)) {
                            try {
                                JSONArray jsonArray = new JSONArray(responseText);
                                for(int i=0;i<jsonArray.length();i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    serverMsg = jsonObject.getString("serverMsg");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                               //     Toast.makeText(getApplicationContext(),"请求超时！请重试"+wifiInfo.getPwd()+haveRecived,Toast.LENGTH_LONG).show();
                               //     Toast.makeText(getApplicationContext(),"请求超时！请重试"+wifiInfo.getSsid()+wifiInfo.getPwd(),Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"获取数据失败，请检查网络！",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
    }
    //初始化组件，文本内容,wifi组件
    public void init(Bundle savedInstanceState){
         wifi = Wifi.builder(this);
         wifi.openWifi();
         userInfo = new AppStore.User();
         ticketActiveInfo = new AppStore.UserTicket();
         wifiInfo = new AppStore.WifiInfo();
         titlebar = (TitleBar) findViewById(R.id.tick_title);
         active = (ImageButton)findViewById(R.id.active);
         //设置按钮状态
         use = (ImageButton)findViewById(R.id.use);
         //门票详情数据存储至deal中
         deal = new ArrayList<TextView>();
         deal.add((TextView) findViewById(R.id.price));
         deal.add((TextView) findViewById(R.id.time));
         deal.add((TextView) findViewById(R.id.status));
         deal.add((TextView) findViewById(R.id.help));
         //获取门票图片，门票地点，名称组件
         ticket_name = (TextView)findViewById(R.id.title_name);
         ticket_picture = (ImageView)findViewById(R.id.ticket_picture);
         progressDialog = new ProgressDialog(this);
         progressDialog.setMessage("正在激活...");
         progressDialog_success = new ProgressDialog(this);
         progressDialog_success.setMessage("激活成功！");
         initGates(Integer.parseInt(getIntent().getStringExtra("gatesNum")));
    }

     public boolean  loadDate(){  //加载数据
         deal.get(2).setText(R.string.status_active);
         deal.get(3).setText(R.string.help_active);
         titlebar.setText(R.string.active);

        return false;
     }

     public void  initGates(int size){
         gatesName = new String[size];
         gateNum = new String[size];
         for(int i=0;i<size;i++) {
             gatesName[i] = i + 1 + "号门";
             gateNum[i] = i+1+"";
         }
     }

     public void use(){
         wifi.closeWifi();
     }

     public void  active(){
         wifi.openWifi();
     }

     public boolean reloadDate(){
         deal.get(2).setText(R.string.status_use);
         deal.get(3).setText(R.string.help_use);
         titlebar.setText(R.string.use);
         active.setVisibility(View.INVISIBLE);
         use.setVisibility(View.VISIBLE);
         return  true;
     }

     public void  setActiveInfo(String code,String mac,String gateLocate,String phone,int ticketType,String areaid){  //设置用户要发送的验证数据
        ;  ticketActiveInfo.setTicketCode(code);
           ticketActiveInfo.setGateLocate(gateLocate);
           ticketActiveInfo.setMacAddress(mac);
           ticketActiveInfo.setPhone(phone);
           ticketActiveInfo.setTicketType(ticketType);
           ticketActiveInfo.setAreaid(areaid);
     }
}
