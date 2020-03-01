package com.easyticket.et.zxy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.View.XRecyclerView;
import com.easyticket.et.zxy.adpter.TicketAdapter;
import com.easyticket.et.zxy.data.ServerMsg;
import com.easyticket.et.zxy.util.Data;
import com.easyticket.et.zxy.util.GetUser;
import com.easyticket.et.zxy.util.HttpUtil;
import com.easyticket.et.zxy.util.LogHandle;
import com.easyticket.et.zxy.util.Utility;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Mine extends BaseActivity {
    private List<LinearLayout> sets;
    private boolean LOGOUT;
    private boolean Recive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        init();
        sets.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"123456",Toast.LENGTH_LONG).show();
            }
        });
        sets.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sets.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"12312312356",Toast.LENGTH_LONG).show();
            }
        });
        sets.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sets.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sets.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sets.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogOut(Data.get("phone",getApplicationContext()));

               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       int i = 0;
                       while(true){
                           if(LOGOUT)
                           {
                               Data.delete("phone",getApplicationContext());
                               startActivity(new Intent(Mine.this,LoginActivity.class));
                               LOGOUT = false;
                               break;
                           }
                           try {
                               Thread.sleep(1000);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                           i++;
                           if(i>3)
                               break;
                       }
                       if(i>=3)
                           Toast.makeText(getApplicationContext(),"LogoutFail",Toast.LENGTH_LONG).show();
                   }
               }).start();

            }
        });
    }

    public void init(){
        sets  = new ArrayList<LinearLayout>();
        sets.add((LinearLayout) findViewById(R.id.person_info));
        sets.add((LinearLayout) findViewById(R.id.keySet));
        sets.add((LinearLayout) findViewById(R.id.myWallet));
        sets.add((LinearLayout) findViewById(R.id.pay_set));
        sets.add((LinearLayout) findViewById(R.id.phone_bind));
        sets.add((LinearLayout) findViewById(R.id.about_us));
        sets.add((LinearLayout) findViewById(R.id.logout));
    }

    public void  requestLogOut(final String phone) {
        String Url =  GetUser.url()+"Logout?phone=" + phone;
        HttpUtil.sendOkHttpRequest(Url,
                new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseText = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                         if(!TextUtils.isEmpty(responseText)){
                             try {
                                 JSONArray jsonArray = new JSONArray(responseText);
                                 if(jsonArray!=null)
                                 for(int i=0;i<jsonArray.length();i++) {
                                     JSONObject jsonObject = jsonArray.getJSONObject(i);
                                     if(jsonObject!=null){
                                         String status = jsonObject.getString("serverMsg");

                                         if(ServerMsg.LogoutSuccess.equals(status))
                                         {   LOGOUT = true;
                                         Recive = true;
                                      //   Toast.makeText(getApplicationContext(),status,Toast.LENGTH_LONG).show();
                                           return;
                                         }
                                         else
                                             LOGOUT = false;
                                     }
                                 }

                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }

                         }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"连接失败",Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                });

    }
}
