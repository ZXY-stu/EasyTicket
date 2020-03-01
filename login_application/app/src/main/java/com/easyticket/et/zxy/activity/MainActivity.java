package com.easyticket.et.zxy.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.easyticket.et.zxy.R;
import org.litepal.LitePal;

import java.security.Key;


public class MainActivity extends TabActivity {
    private TabHost tabhost;
    private TabHost.TabSpec first;
    private TabHost.TabSpec second;
    private TabHost.TabSpec third;
    private long exitTime = 0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);
        init();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }


    private void init() {
        tabhost = this.getTabHost();
        first = tabhost.newTabSpec("homepage");
        second = tabhost.newTabSpec("deal");
        third = tabhost.newTabSpec("mine");

        // 指定选项卡上文字，图标

        first.setIndicator(createContent("主页", R.drawable.first_tab));
        second.setIndicator(createContent("订单", R.drawable.two_tab));
        third.setIndicator(createContent("我的", R.drawable.third_tab));


        // 绑定显示的页面
        // homepage.setContent(R.id.ll_first);
        first.setContent(new Intent(this, HomePage.class));
        second.setContent(new Intent(this,Deal.class));
        third.setContent(new Intent(this,Mine.class));

        // 将选项卡加进TabHost
        tabhost.addTab(first);
        tabhost.addTab(second);
        tabhost.addTab(third);

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
        if (tabId.equals("homepage")) {
            tabhost.setCurrentTabByTag("主页");
        } else if (tabId.equals("deal")) {
            tabhost.setCurrentTabByTag("订单");
        } else if (tabId.equals("mine")) {
            tabhost.setCurrentTabByTag("我的");
        }

    }

	/*
	 * // 设置选项卡上的布局内容 private View createContent(String text, Drawable drawable)
	 * { View view = LayoutInflater.from(this).inflate(R.layout.tabwidget, null,
	 * false); ImageView img_name = (ImageView)
	 * view.findViewById(R.id.img_name); TextView tv_name = (TextView)
	 * view.findViewById(R.id.tv_name); img_name.setImageDrawable(drawable);
	 * tv_name.setText(text); return view; }
	 */

    // 返回单个选项
    private View createContent(String text, int resid) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabwidget, null,
                false);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.img_name);
        tv_name.setText(text);
        iv_icon.setBackgroundResource(resid);
        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }

    }
}
