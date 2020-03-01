package com.easyticket.et.zxy.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easyticket.et.zxy.R;

/**
 * Created by zxy on 2018/3/15.
 */

public class TitleBar extends LinearLayout{
        private ImageButton back;
        private TextView name;
        public TitleBar(Context context, AttributeSet attributeSet) {
          super(context, attributeSet);
          LayoutInflater.from(context).inflate(R.layout.titlebar, this);
          back = (ImageButton) findViewById(R.id.back);
          name = (TextView) findViewById(R.id.title_name);
          back.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View v) {
                  ((Activity)getContext()).finish();
              }
          });
        }
        public void setText(int resid){
           name.setText(resid);
        }


}
