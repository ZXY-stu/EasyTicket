package com.easyticket.et.zxy.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.util.GetUser;

public class RegisterActivity extends BaseActivity {
    private   WebView webview;




     public class WebHost {
        public Context context;

        public WebHost(Context context){
            this.context = context;
        }

         @JavascriptInterface
         public void toLogin(){
             Intent it=new Intent(context,LoginActivity.class);
             startActivity(it);
         }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        webview = findViewById(R.id.register_view);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new WebHost(getApplicationContext()),"js");
        webview.setWebViewClient(new WebViewClient());
        String url= GetUser.url()+"toUserRegister";
        webview.loadUrl(url);
    }






}
