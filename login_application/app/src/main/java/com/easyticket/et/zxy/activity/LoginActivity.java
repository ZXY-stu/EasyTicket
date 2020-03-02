package com.easyticket.et.zxy.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easyticket.et.zxy.R;
import com.easyticket.et.zxy.data.AppStore;
import com.easyticket.et.zxy.data.ServerMsg;
import com.easyticket.et.zxy.util.Data;
import com.easyticket.et.zxy.util.GetUser;
import com.easyticket.et.zxy.util.HttpUtil;
import com.easyticket.et.zxy.util.Utility;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import static android.Manifest.permission.READ_CONTACTS;
public class LoginActivity extends BaseActivity {
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    private UserLoginTask mAuthTask = null;

    private EditText mPhoneView;
    private EditText mPasswordView;
    private View mProgressView;
    private TextView setProxy;
    private TextView setIp;
    private TextView register;
    private ProgressDialog progressDialog;
    private ProgressDialog progressDialog_success;
    private final int Login_SUCCESS = 0;
    private final int Login_Fail = 1;
    private Handler handler = new Handler(){
        //加入传消息来了就这么么办
        public void handleMessage(Message msg){
            switch(msg.what) {
                case  Login_SUCCESS: {
                    progressDialog.dismiss();
                    break;
                }
                case Login_Fail: {
                    progressDialog_success.dismiss();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       GetUser.init(this);
       // Toast.makeText(this,GetUser.url(),Toast.LENGTH_LONG).show();

        // Set up the login form.
        mPhoneView =  (EditText)findViewById(R.id.phone);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在登陆...");
        progressDialog_success = new ProgressDialog(this);
        progressDialog_success.setMessage("登陆成功！");
        register = (TextView)findViewById(R.id.register);
        setProxy = (TextView)findViewById(R.id.proxy);
        setIp = (TextView)findViewById(R.id.setIp);
        mPasswordView = (EditText) findViewById(R.id.pwd);
        setProxy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionStart(getApplicationContext(),setProxyActivity.class,null,null);
            }
        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button login = (Button) findViewById(R.id.Login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionStart(getApplicationContext(),RegisterActivity.class,null,null);
            }
        });

        setIp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               actionStart(getApplicationContext(),SetIpActivity.class,null,null);
             ///   actionStart(getApplicationContext(),RegisterActivity.class,null,null);
            }
        });



    }



    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
      /*  if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Dialog.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }*/
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        mPhoneView.setError(null);
        mPasswordView.setError(null);

        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(phone)) {
            mPhoneView.setError(getString(R.string.error_field_required));
            focusView = mPhoneView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            progressDialog.show();
            new Thread(new UserLoginTask(phone,password)).start();
        }
    }



    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask implements  Runnable{

        private final String mPhone;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mPhone = email;
            mPassword = password;
        }


        public void requestLogin(final String phone, final String pwd) {
            String Url = GetUser.url()+"UserLogin?phone=" + phone + "&pwd=" + pwd;
            HttpUtil.sendOkHttpRequest(Url,
                    new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String responseText = response.body().string();
                            final String status = Utility.handleLogin(responseText, getApplicationContext());


                            if (status != null) {
                                final String[] msg = status.split(":");


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                    //    Toast.makeText(getApplicationContext(), responseText, Toast.LENGTH_LONG).show();
                                        if (ServerMsg.LoginSuccess.equals(msg[0]))

                                            toHome(true, null);
                                         else
                                            toHome(false, msg[1]);


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
                                    Toast.makeText(getApplicationContext(), "连接失败", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
        }



        private  void  toHome(final boolean status,String why){
            progressDialog.dismiss();
            if(status) {
                Data.put("phone",mPhone,getApplicationContext());
                actionStart(getApplicationContext(), MainActivity.class, null, null);
            }
            else
            {
              View focus = null;
                switch(Integer.parseInt(why)){
                    case AppStore.Status.PWD_ERROR:
                        mPasswordView.setError("password error!");
                        focus = mPasswordView;
                        focus.requestFocus();
                        break;
                    case AppStore.Status.ACCOUNT_ERROR:
                        mPhoneView.setError("phone is not exit!");
                        focus = mPhoneView;
                        focus.requestFocus();
                       break;
                }
            }
        }

        @Override
        public void run() {
            requestLogin(mPhone,mPassword);
        }
    }

    public  void actionStart(Context context, Class activity, List<String> params, List<String> datas) {
        Intent intent = new Intent(context, activity);

        int p = 0;
        if (params != null && datas != null)
            for (String data : datas)
                intent.putExtra(params.get(p++), data);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        finish();
    }
}


