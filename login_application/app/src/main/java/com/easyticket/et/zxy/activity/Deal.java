package com.easyticket.et.zxy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.adpter.TypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class Deal extends BaseActivity {
    private GridView gv_type;
    private TypeAdapter typeAdapter;
    private ArrayList<Integer> types;
    private ArrayList<Integer> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        initCategory();
    }

    public void initCategory() {
        this.gv_type = ((GridView) findViewById(R.id.gv_type));
        this.types = new ArrayList<Integer>();
        types.add(R.drawable.have_use);
        types.add(R.drawable.havepurchase);
        types.add(R.drawable.waitpay);
        types.add(R.drawable.refund);
        names = new ArrayList<Integer>();
        names.add(R.string.allDeal);
        names.add(R.string.have_purchase);
        names.add(R.string.noPay);
        names.add(R.string.refund);
        this.typeAdapter = new TypeAdapter(types, names, this,R.layout.grid_view_type);
        this.gv_type.setAdapter(this.typeAdapter);
        this.gv_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                    switch (paramAnonymousInt) {
                        default:
                            return;
                        case 0:
                            startActivity(new Intent(Deal.this, AllActivity.class));
                            return;
                        case 1:
                            startActivity(new Intent(Deal.this, MineTicket.class));
                            return;
                        case 2:
                            startActivity(new Intent(Deal.this, NoPayActivity.class));
                            return;
                        case 3:
                            startActivity(new Intent(Deal.this, Refund.class));
                    }
                }
            }
        );


    }
}