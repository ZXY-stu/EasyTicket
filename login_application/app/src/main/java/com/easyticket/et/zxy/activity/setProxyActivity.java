package com.easyticket.et.zxy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.util.GetUser;

public class setProxyActivity extends BaseActivity {
    private WebView webview;


    public class WebHost {
        public Context context;

        public WebHost(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void toLogin() {
            Intent it = new Intent(context, LoginActivity.class);
            startActivity(it);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_proxy);
        webview = findViewById(R.id.setProxy_view);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new com.easyticket.et.zxy.activity.setProxyActivity.WebHost(getApplicationContext()), "js");
        webview.setWebViewClient(new WebViewClient());
        String url = GetUser.url() + "toSetProxy";
        webview.loadUrl(url);
    }
}

