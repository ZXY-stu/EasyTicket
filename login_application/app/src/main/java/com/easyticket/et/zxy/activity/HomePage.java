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

public class HomePage extends BaseActivity {
    private GridView gv_type;
    private ArrayList<Integer> types;
    private ArrayList<Integer> names;
    private TypeAdapter typeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        initCategory();
    }
    public void initCategory() {
        this.gv_type = ((GridView) findViewById(R.id.gv_type));
        this.types = new ArrayList<Integer>();
        types.add(R.drawable.subway_it);
        types.add(R.drawable.car);
        types.add(R.drawable.movie);
        types.add(R.drawable.music);
        types.add(R.drawable.park);
        types.add(R.drawable.ktv);
        types.add(R.drawable.anim);
        types.add(R.drawable.more);
        names = new ArrayList<Integer>();
        names.add(R.string.subway);
        names.add(R.string.car);
        names.add(R.string.movie);
        names.add(R.string.music);
        names.add(R.string.park);
        names.add(R.string.ktv);
        names.add(R.string.animal);
        names.add(R.string.more);
        this.typeAdapter = new TypeAdapter(types,names, this,R.layout.type_gridview_item);
        this.gv_type.setAdapter(this.typeAdapter);
       this.gv_type.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                switch (paramAnonymousInt)
                {
                    default:
                        return;
                    case 0:
                        Toast.makeText(getApplicationContext(),"dfdfdsf",Toast.LENGTH_LONG).show();
                     //   startActivity(new Intent(HomePage.this,Car_Subway.class));
                        startActivity(new Intent(HomePage.this,NoPayActivity.class));
                        return;
                    case 1:
                        startActivity( new Intent(HomePage.this, AllActivity.class));
                        return;
                    case 2:
                       startActivity(new Intent(HomePage.this, MoveActivity.class));
                        return;
                    case 3:
                        startActivity(new Intent( HomePage.this, MineTicket.class));
                        return;
                    case 4:
                        startActivity(new Intent( HomePage.this, Car_Subway.class));
                        return;
                    case 5:
                      startActivity(new Intent( HomePage.this, Sight.class));
                        return;
                    case 6:
                       startActivity(new Intent( HomePage.this, Sight.class));
                        return;
                    case 7:
                        startActivity(new Intent( HomePage.this, AllActivity.class));
                }

            }
        });
    }
}
