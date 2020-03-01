package com.easyticket.et.zxy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.util.GetUser;

public class SetIpActivity extends AppCompatActivity {
    private EditText setIp;
    private Button Ipsumbit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ip);
        setIp = (EditText)findViewById(R.id.inputIP);
        Ipsumbit = (Button)findViewById(R.id.ipSumbit);

        Ipsumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumbit();
            }
        });
    }
    public void sumbit(){
        String ip = setIp.getText().toString();
        GetUser.setUrl(ip);
        Toast.makeText(this,GetUser.url(),'1').show();
       startActivity(new Intent(this,LoginActivity.class));
    }
}
