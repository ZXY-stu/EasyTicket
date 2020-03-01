package com.easyticket.et.zxy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.View.XRecyclerView;
import com.easyticket.et.zxy.adpter.TicketAdapter;
import com.easyticket.et.zxy.util.GetUser;
import com.easyticket.et.zxy.util.HttpUtil;
import com.easyticket.et.zxy.util.TitleBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import  com.easyticket.et.zxy.data.AppStore.*;
import com.easyticket.et.zxy.util.Utility;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NoPayActivity extends BaseActivity {
    private List<Ticket> ticketList;
    private TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_pay);
        titleBar = findViewById(R.id.noPay_titleBar);
        titleBar.setText(R.string.noPay);
        requestNoPayTicket("");
    }
    public void initTicket(){
        ticketList = new ArrayList<Ticket>();
    /* for(int i=0;i<20;i++)
     ticketList.add(new Ticket("长沙生态公园","X5",
             "￥155","110000+人消费",R.drawable.s2));*/
    }


    public void requestNoPayTicket(final String phone) {
        String Url =  GetUser.url()+"GetUserTickets?phone=" + phone;
        HttpUtil.sendOkHttpRequest(Url,
                new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseText = response.body().string();

                        ticketList =  Utility.handleTicketResponse(responseText);

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


                            }
                        });
                    }
                });
    }
}
