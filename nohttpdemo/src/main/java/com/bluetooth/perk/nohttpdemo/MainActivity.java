package com.bluetooth.perk.nohttpdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView mDisPlay;

    private RequestQueue mRequestQueue;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog = new ProgressDialog(this);
        //1、 创建请求队列, 默认并发3个请求,传入你想要的数字可以改变默认并发数, 例如NoHttp.newRequestQueue(1)。
        mRequestQueue = NoHttp.newRequestQueue(1);
        mDisPlay = (TextView) findViewById(R.id.tv_display);
    }


    private void requestLogin(String username, String password) throws MalformedURLException {
        HashMap<String, String> userinfo = new HashMap<>();
        userinfo.put("username", username);
        userinfo.put("password", password);
        userinfo.put("Language", true ? "zh-CN" : "en-us");

        URL url = new URL("http://www.yunrfid.com/CoreSYS.SYS/LGKeyLogin.index.ajax");

        //2、
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(url.toString(), RequestMethod.POST);
        request.add(userinfo);
        mRequestQueue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {
                mProgressDialog.setMessage("登陆中...");
                mProgressDialog.show();

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {


            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

            }

            @Override
            public void onFinish(int what) {

            }
        });

    }


}
