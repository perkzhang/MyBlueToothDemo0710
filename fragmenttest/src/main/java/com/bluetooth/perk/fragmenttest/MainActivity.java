package com.bluetooth.perk.fragmenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
//静态加载fragment
//    1、继承fragment 重写 onCreateView决定Fragment布局
//    2、在Activity中声明Fragment,就当和普通View控件来用

//    动态加载fragment
//    1、

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
