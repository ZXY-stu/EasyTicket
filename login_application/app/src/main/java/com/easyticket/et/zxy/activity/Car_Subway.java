package com.easyticket.et.zxy.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.easyticket.et.zxy.R;

public class Car_Subway extends TabActivity {

    private TabHost tabhost;
    private TabHost.TabSpec first;
    private TabHost.TabSpec second;
    private long exitTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car__subway);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }


    private void init() {
        tabhost = this.getTabHost();
        first = tabhost.newTabSpec("subway");
        second = tabhost.newTabSpec("car");


        // 指定选项卡上文字，图标

        first.setIndicator(createContent("地铁票", R.drawable.subway_tab));
        second.setIndicator(createContent("汽车票", R.drawable.car_tab));

        // 绑定显示的页面
        first.setContent(new Intent(this, PurchaseSubwayTicket.class));
        second.setContent(new Intent(this, PurchaseCarTicket.class));


        // 将选项卡加进TabHost
        tabhost.addTab(first);
        tabhost.addTab(second);


        tabhost.setCurrentTab(0);
        // 设置tabHost切换时动态更改图标
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                tabChanged(tabId);
            }

        });
    }

    // 捕获tab变化事件
    private void tabChanged(String tabId) {
        // 当前选中项
        if (tabId.equals("subway")) {
            tabhost.setCurrentTabByTag("地铁票");
        } else if (tabId.equals("car")) {
            tabhost.setCurrentTabByTag("汽车票");
        }

    }


    // 返回单个选项
    private View createContent(String text, int resid) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabwidget_car_subway, null,
                false);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.img_name);
        tv_name.setText(text);
        iv_icon.setBackgroundResource(resid);
        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }
}



