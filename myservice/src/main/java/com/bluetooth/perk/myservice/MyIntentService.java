package com.bluetooth.perk.myservice;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by perkzhang on 2016/7/13.
 */
public class MyIntentService extends IntentService {
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
