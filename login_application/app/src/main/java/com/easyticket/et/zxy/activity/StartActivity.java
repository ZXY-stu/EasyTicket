package com.easyticket.et.zxy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.util.Data;
import com.easyticket.et.zxy.util.GetUser;
import com.easyticket.et.zxy.util.LogHandle;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        //   this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        if (Data.get("phone",getApplicationContext()) != null) {
            final Intent toHomePage = new Intent(this, MainActivity.class);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    startActivity(toHomePage);
                    finish();
                }
            }).start();
        }
        else {
            final Intent toHomePage = new Intent(this, LoginActivity.class);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    startActivity(toHomePage);
                    finish();
                }
            }).start();
        }
    }
}
