package com.bluetooth.perk.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by perk on 2016/7/25.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceiver",
                Toast.LENGTH_SHORT).show();
        abortBroadcast();//终止广播继续传递
    }
}
