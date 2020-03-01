package com.easyticket.et.zxy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.easyticket.et.zxy.*;
import com.easyticket.et.zxy.View.XRecyclerView;
import com.easyticket.et.zxy.adpter.TicketAdapter;
import com.easyticket.et.zxy.util.Data;
import com.easyticket.et.zxy.util.GetUser;
import com.easyticket.et.zxy.util.HttpUtil;
import com.easyticket.et.zxy.util.LogHandle;
import com.easyticket.et.zxy.util.TitleBar;
import  com.easyticket.et.zxy.data.AppStore.*;
import com.easyticket.et.zxy.util.Utility;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
public class MineTicket extends BaseActivity {
    private List<Ticket> ticketList;
    private TitleBar titleBar;
    JSONArray a = null;
    JSONObject jsonObject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_ticket);
        titleBar = this.findViewById(R.id.mine_titleBar);
        titleBar.setText(R.string.have_purchase);
        requestMineTicket(Data.get("phone",getApplicationContext()));
    }

    public void requestMineTicket(final String phone) {
        String Url =  GetUser.url()+"GetUserTickets?phone=" + phone;
        HttpUtil.sendOkHttpRequest(Url,
               new Callback() {
                    @Override
            public void onResponse(Call call, Response response) throws IOException {
                        final String responseText = response.body().string();
                       // Toast.makeText(getApplicationContext(),responseText,Toast.LENGTH_LONG).show();
                    ticketList =  Utility.handleTicketResponse(responseText);
                        LogHandle.e(responseText);
                 if(ticketList!=null) {
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             XRecyclerView recyclerView = (XRecyclerView) findViewById(R.id.mine_ticket_view);
                             GridLayoutManager layoutManager = new
                             GridLayoutManager(getApplicationContext(), 1);
                             recyclerView.setLayoutManager(layoutManager);
                             TicketAdapter adapter = new TicketAdapter(ticketList,getApplicationContext(), 3, R.layout.have_purchase_ticket);
                             recyclerView.setAdapter(adapter);

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
Toast.makeText(getApplicationContext(),"连接失败",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }

}
