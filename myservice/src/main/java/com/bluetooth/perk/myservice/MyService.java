package com.bluetooth.perk.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by perk on 2016/7/12.
 */
/*
* 每个服务都要在AndroidManifest中注册才能生效
* 服务中的代码都是默认运行在主线程当中的,所以处理耗时操作的时候就必须开启线程
* 如果想在执行完逻辑之后就自动销毁服务，需要手动销毁服务stopSelf()
* 为了可以简单地创建一个异步的、会自动停止的服务，Android 专门提供了一个
  IntentService 类
* */
public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //Service中唯一的抽象方法
        //在 MyService 中创建DownloadBinder 的实例，然后在 onBind()方法里返回了这个实例
        //当一个活动和服务绑定了之后，就可以调用该服务里的 Binder提供的方法
        Log.d("MyService", "onBind executed");
        return mBinder;

    }

    @Override
    public void onCreate() {
        //服务被“创建”的时候调用
        super.onCreate();
        Log.d("MyService", "onCreate executed");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //服务每次被“启动”的时候调用（这里主要执行业务逻辑）
        Log.d("MyService", "onStartCommand executed");
        //topSelf() == stopService();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        //服务被“销毁”的时候调用（这里回收不再使用的资源）
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }

    //新建了一个 DownloadBinder 类
    class DownloadBinder extends Binder {
        public void startDownLoad(){
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }

    }
}
